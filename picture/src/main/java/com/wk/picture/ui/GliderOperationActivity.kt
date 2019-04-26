package com.wk.picture.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.wk.common.constant.RouterPath
import com.wk.picture.R
import kotlinx.android.synthetic.main.pic_activity_glider_operation.*

@Route(path = RouterPath.GliderOperationActivity)
class GliderOperationActivity : AppCompatActivity(),View.OnClickListener {
    private val url by lazy { "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg" }
    private val url2 by lazy { "http://p1.pstatp.com/large/166200019850062839d3" }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pic_activity_glider_operation)
        bt.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v){
            bt->{
                Glide.with(this).load(url2).into(iv)
            }
        }
    }
}
