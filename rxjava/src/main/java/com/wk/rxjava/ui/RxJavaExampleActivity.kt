package com.wk.rxjava.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath
import rx.Observable
import java.io.File
import java.util.*

@Route(path = RouterPath.RxJavaExampleActivity)
class RxJavaExampleActivity : BaseMainListActivity() {
    private val examples by lazy {
        val list = ArrayList<String>()
        list.add("example")
        list
    }

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val position=bundle?.getInt(BundleKey.ItemPosition,-1)
        when(position){
            0->{
                val f= File("wk")
                val fileList=f.listFiles()
                Observable.from(fileList).map {
                    it.name.endsWith(".txt")
                }.subscribe{

                }
            }
        }
    }


    override fun getRecyclerItemList()=examples


}
