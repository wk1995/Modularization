package com.wk.ipc.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.constant.RouterPath
import com.wk.ipc.messenger.MessengerActivity
import kotlinx.android.synthetic.main.ipc_activity_messenger.*
import timber.log.Timber

@Route(path = RouterPath.ACTIVITY_AIDL)
class AIDLActivity : MessengerActivity() {

    private var remote: IWkManager? = null
    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Timber.i("onServiceConnected Thread: ${Thread.currentThread().name}")
            val binder = IWkManager.Stub.asInterface(service)
            remote = binder
            Timber.i("binder class is ${binder.javaClass}")
            val list = binder.wkList
            Timber.i("list`class is ${list.javaClass}")
            Timber.i("list is $list")
            binder.addWk(Wk("3"))
            val newList = binder.wkList
            Timber.i("newList is $newList")
            binder.registerListener(wkListener)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            btBindService -> {
                val intent = Intent(this, AIDLService::class.java)
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
            }
            btUnBindService -> unbindService(mServiceConnection)
        }

    }

    private val wkListener = object : IAddWkListener.Stub() {
        //workThread
        override fun listener(wk: Wk?) {
            Timber.i("listener Thread: ${Thread.currentThread().name}")
            Timber.i("receive $wk")
        }
    }

    override fun onDestroy() {
        if (remote?.asBinder()?.isBinderAlive == true)
            remote?.unregisterListener(wkListener)
        unbindService(mServiceConnection)
        super.onDestroy()
    }
}
