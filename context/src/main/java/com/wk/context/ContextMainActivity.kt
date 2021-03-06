package com.wk.context

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath

@Route(path = RouterPath.ContextMainActivity)
class ContextMainActivity : BaseMainListActivity() {
    private val list by lazy {
        val list = ArrayList<String>()
        list.add(ACTIVITY)
        list.add(SERVICE)
        list
    }

    companion object {
        private const val ACTIVITY = "ACTIVITY"
        private const val SERVICE = "SERVICE"
    }

    override fun getRecyclerItemList() = list

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val itemText = bundle?.getString(BundleKey.ItemText)
        when (itemText) {
            ACTIVITY -> {
                ARouter.getInstance().build(RouterPath.ActivityMainActivity).navigation()
            }
            SERVICE -> ARouter.getInstance().build(RouterPath.ServiceMainActivity).navigation()
        }
    }
}
