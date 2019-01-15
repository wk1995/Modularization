package com.wk.ipc.aidl

import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import android.os.RemoteCallbackList
import timber.log.Timber
import java.util.concurrent.CopyOnWriteArrayList

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2018/12/30
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
class AIDLService : Service() {

    private var isDestroy = true

    private val aidls by lazy { CopyOnWriteArrayList<Wk>() }
    //    private val mIAddWkListeners by lazy { CopyOnWriteArrayList<IAddWkListener>() }
    private val mIAddWkListeners by lazy { RemoteCallbackList<IAddWkListener>() }

    private val aidlInterface = object : IWkManager.Stub() {

        override fun addWk(wk: Wk?) {
            aidls.add(wk)
        }

        override fun getWkList(): MutableList<Wk> {
            return aidls
        }

        override fun registerListener(mIAddWkListener: IAddWkListener?) {
            mIAddWkListeners.register(mIAddWkListener)
            Timber.i("registerListener")
        }

        override fun unregisterListener(mIAddWkListener: IAddWkListener?) {
            mIAddWkListeners.unregister(mIAddWkListener)
            Timber.i("unregisterListener")
        }
    }

    override fun onCreate() {
        isDestroy = false
        super.onCreate()
        aidls.add(Wk("1"))
        aidls.add(Wk("2"))
        Thread(ServiceWorkder()).start()
    }

    override fun onDestroy() {
        isDestroy = true
        super.onDestroy()
    }

    private fun listener(wk: Wk) {
        aidls.add(wk)
        val count = mIAddWkListeners.beginBroadcast()
        Timber.i("listener size is $count")
        for (i in 0 until count) {
            val mListener = mIAddWkListeners.getBroadcastItem(i)
            mListener?.listener(wk)
        }
        mIAddWkListeners.finishBroadcast()
    }

    inner class ServiceWorkder : Runnable {
        var i = 1000
        override fun run() {
            while (!isDestroy) {
                Thread.sleep(5000)
                listener(Wk(i.toString()))
                i--
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        val check = checkCallingOrSelfPermission("com.wk.ipc.ACCESS_WK_SERVICE")
        return if (check != PackageManager.PERMISSION_DENIED) {
            Timber.i("验证通过")
            aidlInterface
        } else {
            Timber.i("验证没通过")
            return null
        }
    }
}