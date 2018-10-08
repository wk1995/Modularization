package com.wk.common.base

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wk.common.R
import com.wk.common.adapter.MyAdapter
import com.wk.common.transmission.RecyclerItemListener
import kotlinx.android.synthetic.main.common_main_activity.*

/**
 * <pre>
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2018/08/23
 *      desc   : module的MainActivity的父类，它就一个RecyclerView，item就是StringList
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * </pre>
 */
abstract class BaseMainListActivity:BaseActivity(), RecyclerItemListener {
    private val myAdapter by lazy {
        MyAdapter(getRecyclerItemList(),this)
    }

    final override fun getLayoutResource()= R.layout.common_main_activity

    override fun initView() {
        rvMainList.layoutManager= LinearLayoutManager(this)
        rvMainList.adapter=myAdapter
    }

    override fun initListener() {
    }

    abstract fun getRecyclerItemList():List<String>


}