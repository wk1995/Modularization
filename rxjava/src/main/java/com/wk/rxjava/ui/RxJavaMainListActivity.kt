package com.wk.rxjava.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath

@Route(path = RouterPath.RxJavaMainActivity)
class RxJavaMainListActivity : BaseMainListActivity() {

    private val operationList by lazy {
        val list = ArrayList<String>()
        list.add("创建操作符")
        list.add("变换操作符")
        list.add("辅助操作符")
        list.add("example")
        list
    }

    override fun getRecyclerItemList() = operationList

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val itemText = bundle?.getString(BundleKey.ItemText)
        when (itemText) {
            operationList[0]  -> ARouter
                    .getInstance()
                    .build(RouterPath.RxJavaCreationActivity)
                    .navigation()
            operationList[1] -> ARouter
                    .getInstance()
                    .build(RouterPath.RxJavaChangeActivity)
                    .navigation()
            operationList[2] -> ARouter
                    .getInstance()
                    .build(RouterPath.RxJavaAuxiliaryActivity)
                    .navigation()
            operationList[3] -> ARouter
                    .getInstance()
                    .build(RouterPath.RxJavaAuxiliaryActivity)
                    .navigation()
        }
    }
}
