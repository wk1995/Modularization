package com.wk.list.rv.click

import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.constant.RouterPath
import com.wk.list.R
import com.wk.list.rv.BaseRvBrvahActivity
import kotlinx.android.synthetic.main.list_activity_rv_brvah.*
import timber.log.Timber

@Route(path = RouterPath.RvClickCallBackActivity)
class RvClickCallBackActivity : BaseRvBrvahActivity(), ClickCallBackAdapter.EtTextCallBack {


    override fun initView() {
        super.initView()
        val adapter = ClickCallBackAdapter(this)
        val clickCallBackBeans = ArrayList<ClickCallBackBean>()
        for (i in 0 until 20)
            clickCallBackBeans.add(ClickCallBackBean("note$i"))
        adapter.setOnItemChildLongClickListener { _, view, position ->
            when (view?.id) {
                R.id.tvUserName -> {
                    val clickCallBackBean = clickCallBackBeans[position]
                    clickCallBackBean.isShow = !clickCallBackBean.isShow
                    adapter.notifyDataSetChanged()
                }
            }
            true

        }

        rvBTest.layoutManager = LinearLayoutManager(this)
        rvBTest.adapter = adapter

        adapter.setNewData(clickCallBackBeans)

    }

    override fun afterTextChanged(s: Editable?, position: Int) {
        Timber.d(" afterTextChanged text: $s position: $position")
    }
}
