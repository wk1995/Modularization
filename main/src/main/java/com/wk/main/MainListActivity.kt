package com.wk.main

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath

class MainListActivity : BaseMainListActivity() {
    private val activityList by lazy {
        val activityList=ArrayList<String>()
        activityList.add("hotfix")
        activityList.add("list")
        activityList.add("picture")
        activityList.add("view")
        activityList.add("rxJava")
        activityList.add("okHttp")
        activityList.add("retrofit")
        activityList.add("fragment")
        activityList
    }
    override fun getRecyclerItemList()=activityList

    override fun onRecyclerItemClick(bundle: Bundle?,vararg objects: Any?) {
        val itemText=bundle?.getString(BundleKey.ItemText)
        when(itemText){
            activityList[0]->ARouter
                    .getInstance()
                    .build(RouterPath.HotfixActivityMainActivity)
                    .navigation()
            activityList[1]->ARouter
                    .getInstance()
                    .build(RouterPath.ListActivityMainActivity)
                    .navigation()
            activityList[2]->ARouter
                    .getInstance()
                    .build(RouterPath.PicMainActivity)
                    .navigation()
            activityList[3]->ARouter
                    .getInstance()
                    .build(RouterPath.ViewMainActivity)
                    .navigation()
            activityList[4]->ARouter
                    .getInstance()
                    .build(RouterPath.RxJavaMainActivity)
                    .navigation()
            activityList[5]->ARouter
                    .getInstance()
                    .build(RouterPath.OkHttpMainActivity)
                    .navigation()
            activityList[6]->ARouter
                    .getInstance()
                    .build(RouterPath.RetrofitMainActivity)
                    .navigation()
            activityList[7]->ARouter
                    .getInstance()
                    .build(RouterPath.FragmentMainListActivity)
                    .navigation()
        }

    }

}
