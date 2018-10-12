package com.wk.modularization.fragment.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.constant.RouterPath
import com.wk.list.R
import com.wk.modularization.fragment.Container
import com.wk.modularization.fragment.SinglePaneContainer

@Route(path = RouterPath.ViewReplaceFragmentActivity)
class ViewReplaceFragmentActivity : AppCompatActivity() {
    lateinit var container: Container
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        container = findViewById<SinglePaneContainer>(R.id.container)
    }

}
