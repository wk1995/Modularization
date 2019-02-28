package com.wk.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath

@Route(path = RouterPath.ViewMainActivity)
class ViewMainListActivity : BaseMainListActivity() {
    companion object {
        const val IMAGE_VIEW = "ImageView"
        const val REMOTE_VIEWS = "RemoteViews"
        const val TOOL_BAR = "toolbar"
    }

    private val operationList by lazy {
        val list = ArrayList<String>()
        list.add(IMAGE_VIEW)
        list.add(REMOTE_VIEWS)
        list.add(TOOL_BAR)
        list
    }

    override fun getRecyclerItemList() = operationList

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val itemText = bundle?.getString(BundleKey.ItemText)
        when (itemText) {
            REMOTE_VIEWS -> ARouter
                    .getInstance()
                    .build(RouterPath.RemoteMainListActivity)
                    .navigation()
            IMAGE_VIEW ->
                ARouter
                        .getInstance()
                        .build(RouterPath.IvOperationActivity)
                        .navigation()
            TOOL_BAR -> ARouter
                    .getInstance()
                    .build(RouterPath.ToolBarActivity)
                    .navigation()
        }
    }

}
