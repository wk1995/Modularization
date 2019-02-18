package com.wk.context.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath
import timber.log.Timber

@Route(path = RouterPath.ServiceMainActivity)
class ServiceMainActivity : BaseMainListActivity() {
    companion object {
        const val START = "start"
        const val BIND = "bind"
        const val BIND_AND_START = "bind_and_start"
    }

    private val myServiceConnection by lazy { MyServiceConnection() }
    private val startServiceIntent by lazy { Intent(this@ServiceMainActivity, StartService::class.java) }
    private val startBindIntent by lazy { Intent(this@ServiceMainActivity, BindService::class.java) }
    private val flagRun by lazy { HashMap<String, Boolean?>() }
    override fun getRecyclerItemList(): List<String> {
        val list = ArrayList<String>()
        list.add(START)
        list.add(BIND)
        list.add(BIND_AND_START)
        return list
    }

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val itemText = bundle?.getString(BundleKey.ItemText) ?: return
        val isNotRun = flagRun[itemText] != true
        when (itemText) {
            START -> {
                if (isNotRun) {
                    startService(startServiceIntent)
                } else {
                    stopService(startServiceIntent)
                }

            }
            BIND -> {
                if (isNotRun)
                    bindService(Intent(this@ServiceMainActivity, BindService::class.java),
                            myServiceConnection, Context.BIND_AUTO_CREATE)
                else
                    unbindService(myServiceConnection)
            }
            BIND_AND_START ->
                if (isNotRun) {
                    startService(startBindIntent)
                    bindService(startBindIntent,
                            myServiceConnection, Context.BIND_AUTO_CREATE)
                } else {

                    stopService(startServiceIntent)
                    unbindService(myServiceConnection)
                }
        }
        flagRun[itemText] = isNotRun
    }

    class MyServiceConnection : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Timber.i("onServiceDisconnected ")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Timber.i("onServiceConnected IBinder`class is ${service?.javaClass?.simpleName}")
        }
    }
}
