package com.wk.picture.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath


@Route(path = RouterPath.PicMainActivity)
class PicMainListActivity : BaseMainListActivity() {

    private val operationList by lazy{
        val list=ArrayList<String>()
        list.add("直接显示，解码后显示，Glider内存比较")
        list.add("Glider")
        list
    }

    override fun getRecyclerItemList()=operationList

    override fun onRecyclerItemClick(bundle: Bundle?,vararg objects: Any?) {
        val itemPosition=bundle?.getInt(BundleKey.ItemPosition,-1)
        when(itemPosition){
            0-> ARouter
                    .getInstance()
                    .build(RouterPath.MemoryComparisonActivity)
                    .navigation()
            1->ARouter
                    .getInstance()
                    .build(RouterPath.GliderOperationActivity)
                    .navigation()
        }
    }
}
