package com.wk.modularization.web

import com.wk.common.base.BaseActivity
import com.wk.view.R
import kotlinx.android.synthetic.main.web_activity_main.*

class WebMainActivity : BaseActivity() {


    override fun getLayoutResource()=R.layout.web_activity_main

    override fun initView() {
        super.initView()
        webView.loadUrl("https://github.com/JakeWharton/butterknife")
    }

    override fun initListener() {
        super.initListener()
    }

    override fun initParam() {
        super.initParam()
    }

}
