package com.wk.ipc.messenger

import android.app.Service
import android.content.Intent
import android.os.*
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
class MessengerService : Service() {
    private val messengerHandler by lazy { MessengerHandler() }

    companion object {
        class MessengerHandler : Handler() {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                val data = msg?.data
                when (msg?.what) {
                    2 -> {
                        val message = data?.getString("service")
                        Timber.d("$message")
                        val messenger = msg.replyTo
                        val m = Message.obtain()
                        m.what = 1
                        val bundle = Bundle()
                        bundle.putString("client", "msg come from service")
                        m.data = bundle
                        messenger?.send(m)
                    }
                }

            }
        }
    }

    private val messenger by lazy { Messenger(messengerHandler) }

    override fun onBind(intent: Intent?): IBinder? = messenger.binder
}