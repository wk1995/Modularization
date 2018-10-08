package com.wk.common.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        initView()
        initListener()
        initParam()
    }

    //布局
    abstract fun getLayoutResource():Int
    //初始化View
    open fun initView(){}
    //设置监听
    open fun initListener(){}
    open fun initParam(){}
}
