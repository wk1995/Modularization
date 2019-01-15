package com.wk.modularization.hardware

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath

@Route(path = RouterPath.HardwareMainActivity)
class HardwareMainActivity : BaseMainListActivity() {

    private val items by lazy {
        val items = ArrayList<String>()
        items.add("音视频")
        items.add("USB")
        items.add("surfaceView")
        items
    }


    override fun getRecyclerItemList() = items

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val position = bundle?.getInt(BundleKey.ItemPosition, -1) ?: return
        when (position) {
            0 ->ARouter.getInstance().build(RouterPath.MediaActivity).navigation()
            2 ->ARouter.getInstance().build(RouterPath.SurfaceActivity).navigation()

        }
    }
}
