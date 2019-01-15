package com.wk.list.rv.simple

import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.constant.RouterPath
import com.wk.list.rv.BaseRvBrvahActivity
import kotlinx.android.synthetic.main.list_activity_rv_brvah.*

@Route(path = RouterPath.RvSimpleOneItemActivity)
class RvSimpleOneItemActivity : BaseRvBrvahActivity() {

    private val userList by lazy {
        val userList=ArrayList<User>()
        for(i in 0 until 30)
        userList.add(User("wk$i", i))

        userList
    }

    override fun initView() {
        super.initView()
        rvBTest.layoutManager=LinearLayoutManager(this)
        rvBTest.adapter= SimpleOneItemAdapter(userList)
    }
}
