package com.wk.ipc.messenger

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.base.BaseActivity
import com.wk.common.constant.RouterPath
import com.wk.ipc.R
import kotlinx.android.synthetic.main.ipc_activity_messenger.*
import timber.log.Timber

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2018/12/28
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
@Route(path = RouterPath.ACTIVITY_MESSENGER)
open class MessengerActivity : BaseActivity(), View.OnClickListener {


    companion object {
        //用来接收Service发送过来的msg
        private class MessengerHandler : Handler() {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                val data = msg?.data
                when (msg?.what) {
                    1 -> {
                        val receive = data?.getString("client")
                        Timber.d("$receive")
                    }
                }

            }
        }
    }

    private val messengerHandler by lazy { MessengerHandler() }
    private val mes by lazy { Messenger(messengerHandler) }

    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val messenger = Messenger(service)
            val msg = Message.obtain()
            msg.what = 2
            val bundle = Bundle()
            bundle.putString("service", "msg come from client")
            msg.data = bundle
            msg.replyTo = mes
            messenger.send(msg)
        }
    }


    override fun getLayoutResource() = R.layout.ipc_activity_messenger
    override fun initListener() {
        super.initListener()
        btBindService.setOnClickListener(this)
        btUnBindService.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v) {
            btBindService -> {
                val intent = Intent(this, MessengerService::class.java)
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
            }
            btUnBindService -> unbindService(mServiceConnection)
        }
    }

    override fun onPause() {
        super.onPause()
        unbindService(mServiceConnection)
    }
}