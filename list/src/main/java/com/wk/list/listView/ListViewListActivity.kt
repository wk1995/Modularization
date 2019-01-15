package com.wk.list.listView

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath

@Route(path = RouterPath.ListViewActivity)
class ListViewListActivity : BaseMainListActivity() {
    private val list by lazy {
        val list = ArrayList<String>()
        list.add("下拉刷新")
        list
    }

    override fun getRecyclerItemList() = list

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val itemText = bundle?.getString(BundleKey.ItemText)
        when (itemText) {
            list[0] -> {
                ARouter.getInstance().build(RouterPath.ListViewPullFlushActivity).navigation()
            }
        }
    }
}
