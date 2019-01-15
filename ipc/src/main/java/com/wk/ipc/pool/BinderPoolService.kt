package com.wk.ipc.pool

import android.app.Service
import android.content.Intent

class BinderPoolService : Service() {
    private val binderPoolImpl = BinderPool.Companion.BinderPoolImp()
    override fun onBind(intent: Intent) = binderPoolImpl
}
