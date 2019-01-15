package com.wk.ipc.pool

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import timber.log.Timber
import java.util.concurrent.CountDownLatch

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2019/1/14
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
class BinderPool(private val context: Context) {
    companion object {
        const val SUM = 0
        const val REDUCE = 1

        class BinderPoolImp : IBinderPool.Stub() {
            override fun getInBinder(binderCode: Int): IBinder? {
                return when (binderCode) {
                    SUM -> SumInterfaceImpl()
                    REDUCE -> ReduceInterfaceImpl()
                    else -> null
                }

            }
        }

    }

    private var countDownLatch: CountDownLatch? = null
    private var binderPoolImpl: IBinderPool? = null

    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            binderPoolImpl = IBinderPool.Stub.asInterface(service)
            binderPoolImpl?.asBinder()?.linkToDeath(mDeathRecipient, 0)
            countDownLatch?.countDown()
        }
    }

    private val mDeathRecipient = MyDeathRecipient()

    inner class MyDeathRecipient : IBinder.DeathRecipient {
        override fun binderDied() {
            Timber.i("binderDied Thread: ${Thread.currentThread().name}")
            binderPoolImpl?.asBinder()?.unlinkToDeath(mDeathRecipient, 0)
            binderPoolImpl = null
            bindService()
        }
    }

    fun bindService() {
        countDownLatch = CountDownLatch(1)
        val intent = Intent(context, BinderPoolService::class.java)
        context.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
        countDownLatch?.await()
    }

    fun unBindService() {
        context.unbindService(mServiceConnection)
        Timber.i("unBindService")
    }


    fun getBinder(binderCode: Int): IBinder? {
        return binderPoolImpl?.getInBinder(binderCode)
    }


}