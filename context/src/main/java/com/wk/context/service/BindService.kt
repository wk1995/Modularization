package com.wk.context.service

import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.wk.common.base.BaseService
import timber.log.Timber

class BindService : BaseService() {

    override fun onBind(intent: Intent?): IBinder? {
        super.onBind(intent)
        return MyBinder()
    }

    class MyBinder : Binder() {
        fun way() {
            Timber.i("MyBinder way")
        }
    }
}
