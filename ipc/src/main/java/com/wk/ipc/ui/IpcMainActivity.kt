package com.wk.ipc.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath

@Route(path = RouterPath.IpcMainActivity)
class IpcMainActivity : BaseMainListActivity() {
    companion object {
        const val MESSENGER = "Messenger"
        const val AIDL = "AIDL"
        const val BINDER_POOL = "BINDER_POOL"
    }

    private val list by lazy {
        val list = ArrayList<String>()
        list.add(MESSENGER)
        list.add(AIDL)
        list.add(BINDER_POOL)
        list
    }


    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val text = bundle?.getString(BundleKey.ItemText) ?: throw Exception("itemText is null")
        when (text) {
            MESSENGER -> ARouter.getInstance().build(RouterPath.ACTIVITY_MESSENGER).navigation()
            AIDL -> ARouter.getInstance().build(RouterPath.ACTIVITY_AIDL).navigation()
            BINDER_POOL -> ARouter.getInstance().build(RouterPath.ACTIVITY_BINDER_POOL).navigation()
        }
    }

    override fun getRecyclerItemList() = list
}
