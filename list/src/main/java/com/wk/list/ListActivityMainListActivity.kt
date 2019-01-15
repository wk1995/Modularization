package com.wk.list

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath
import com.wk.debug.ListApplication
import java.io.File


@Route(path = RouterPath.ListActivityMainActivity)
class ListActivityMainListActivity : BaseMainListActivity(){
    private val list by lazy{
        val list =ArrayList<String>()
        list.add("ListView")
        list.add("RecyclerView")
        list
    }

    override fun getRecyclerItemList()=list

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val itemText=bundle?.getString(BundleKey.ItemText)
        when(itemText){
            list[0]->{
                ARouter.getInstance().build(RouterPath.ListViewActivity).navigation()
            }
            list[1]->
                ARouter.getInstance().build(RouterPath.RvMainListActivity).navigation()
        }
    }
}
