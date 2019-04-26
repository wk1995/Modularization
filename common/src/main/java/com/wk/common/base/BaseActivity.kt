package com.wk.common.base

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.i("${this.javaClass} onCreate")
        super.onCreate(savedInstanceState)
        if (getLayoutResource() != -1)
            setContentView(getLayoutResource())
        initView()
        initListener()
        initParam()
    }

    //布局
    abstract fun getLayoutResource(): Int

    //初始化View
    open fun initView() {}

    //设置监听
    open fun initListener() {}

    open fun initParam() {}

    override fun onRestart() {
        Timber.i("${this.javaClass} onRestart")
        super.onRestart()
    }

    override fun onResume() {
        Timber.i("${this.javaClass} onResume")
        super.onResume()
    }

    override fun onPause() {
        Timber.i("${this.javaClass} onPause")
        super.onPause()
    }

    override fun onStop() {
        Timber.i("${this.javaClass} onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Timber.i("${this.javaClass} onDestroy")
        super.onDestroy()
    }
    override fun onConfigurationChanged(newConfig: Configuration?) {
        Timber.i("${this.javaClass} onConfigurationChanged")
        super.onConfigurationChanged(newConfig)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Timber.i("${this.javaClass} onCreateOptionsMenu")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNewIntent(intent: Intent?) {
        Timber.i("${this.javaClass} onNewIntent")
        super.onNewIntent(intent)
    }
}
