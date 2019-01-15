package com.wk.ipc.pool

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.constant.RouterPath
import com.wk.ipc.messenger.MessengerActivity
import com.wk.ipc.pool.BinderPool.Companion.REDUCE
import com.wk.ipc.pool.BinderPool.Companion.SUM
import kotlinx.android.synthetic.main.ipc_activity_messenger.*
import timber.log.Timber

@Route(path = RouterPath.ACTIVITY_BINDER_POOL)
class BinderPoolActivity : MessengerActivity() {
    private val mBinderPool by lazy { BinderPool(this) }

    override fun onClick(v: View?) {
        when (v) {
            btBindService -> {
                Thread {
                    doWork()
                }.start()


            }
            btUnBindService -> {
                mBinderPool.unBindService()
            }
        }

    }

    private fun doWork() {
        mBinderPool.bindService()
        val sum = ISumInterface.Stub.asInterface(mBinderPool.getBinder(SUM)) as SumInterfaceImpl
        val reduce = IReduceInterface.Stub.asInterface(mBinderPool.getBinder(REDUCE)) as ReduceInterfaceImpl
        Timber.i("sum: ${sum.add(1, 2)}")
        Timber.i("reduce: ${reduce.reduce(1, 2)}")

    }
}