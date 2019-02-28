package com.wk.common.base

import android.app.Service
import android.content.Intent
import android.os.IBinder
import timber.log.Timber

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2019/2/18
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
abstract class BaseService:Service() {

    override fun onCreate() {
        Timber.i("${this.javaClass.simpleName }  onCreate Thread： ${Thread.currentThread().name}")
        super.onCreate()
    }

    override fun onBind(intent: Intent?): IBinder? {
        Timber.i("${this.javaClass.simpleName }  onBind Thread： ${Thread.currentThread().name}")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.i("${this.javaClass.simpleName }  onStartCommand Thread： ${Thread.currentThread().name}")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Timber.i("${this.javaClass.simpleName }  onDestroy Thread： ${Thread.currentThread().name}")
        super.onDestroy()
    }

}