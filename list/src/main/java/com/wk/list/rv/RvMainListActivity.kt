package com.wk.list.rv

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath

@Route(path = RouterPath.RvMainListActivity)
class RvMainListActivity : BaseMainListActivity() {

    companion object {
        private const val simpleOneItem = "simpleOneItem"
        private const val clickCallBack = "点击事件以及回调"
        private const val expand = "三级伸缩，并解决多选框的复用"
        private const val drag = "拖拽"
    }

    private val items by lazy {
        val items = ArrayList<String>()
        items.add(simpleOneItem)
        items.add(clickCallBack)
        items.add(expand)
        items.add(drag)
        items
    }

    override fun getRecyclerItemList() = items

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val itemText = bundle?.getString(BundleKey.ItemText) ?: return
        when (itemText) {
            simpleOneItem -> ARouter.getInstance().build(RouterPath.RvSimpleOneItemActivity).navigation()
            clickCallBack -> ARouter.getInstance().build(RouterPath.RvClickCallBackActivity).navigation()
            expand -> ARouter.getInstance().build(RouterPath.RvExpandActivity).navigation()
            drag -> ARouter.getInstance().build(RouterPath.RvDragActivity).navigation()
        }
    }
}