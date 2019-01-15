package com.wk.main

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath

class MainListActivity : BaseMainListActivity() {

    companion object {
        const val HOTFIX="hotfix"
        const val LIST="list"
        const val PICTURE="picture"
        const val VIEW="view"
        const val RX_JAVA="rxJava"
        const val OK_HTTP="okHttp"
        const val RETROFIT="retrofit"
        const val FRAGMENT="fragment"
        const val WEB="web"
        const val HARD_WARE="hardware"
        const val IPC="ipc"
    }

    private val activityList by lazy {
        val activityList=ArrayList<String>()
        activityList.add(HOTFIX)
        activityList.add(LIST)
        activityList.add(PICTURE)
        activityList.add(VIEW)
        activityList.add(RX_JAVA)
        activityList.add(OK_HTTP)
        activityList.add(RETROFIT)
        activityList.add(FRAGMENT)
        activityList.add(WEB)
        activityList.add(HARD_WARE)
        activityList.add(IPC)
        activityList
    }
    override fun getRecyclerItemList()=activityList

    override fun onRecyclerItemClick(bundle: Bundle?,vararg objects: Any?) {

        val itemText=bundle?.getString(BundleKey.ItemText)
        when(itemText){
            HOTFIX->ARouter
                    .getInstance()
                    .build(RouterPath.HotfixActivityMainActivity)
                    .navigation()
            LIST->ARouter
                    .getInstance()
                    .build(RouterPath.ListActivityMainActivity)
                    .navigation()
            PICTURE->ARouter
                    .getInstance()
                    .build(RouterPath.PicMainActivity)
                    .navigation()
            VIEW->ARouter
                    .getInstance()
                    .build(RouterPath.ViewMainActivity)
                    .navigation()
            RX_JAVA->ARouter
                    .getInstance()
                    .build(RouterPath.RxJavaMainActivity)
                    .navigation()
            OK_HTTP->ARouter
                    .getInstance()
                    .build(RouterPath.OkHttpMainActivity)
                    .navigation()
            RETROFIT->ARouter
                    .getInstance()
                    .build(RouterPath.RetrofitMainActivity)
                    .navigation()
            FRAGMENT->ARouter
                    .getInstance()
                    .build(RouterPath.FragmentMainListActivity)
                    .navigation()
            WEB->ARouter
                    .getInstance()
                    .build(RouterPath.WebMainActivity)
                    .navigation()
            HARD_WARE->ARouter
                    .getInstance()
                    .build(RouterPath.HardwareMainActivity)
                    .navigation()
            IPC->ARouter
                    .getInstance()
                    .build(RouterPath.IpcMainActivity)
                    .navigation()
        }

    }

}
