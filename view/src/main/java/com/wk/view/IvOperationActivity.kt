package com.wk.view

import android.graphics.Matrix
import android.view.View
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.wk.common.base.BaseActivity
import com.wk.common.constant.RouterPath
import kotlinx.android.synthetic.main.view_activity_iv_operation.*

@Route(path = RouterPath.IvOperationActivity)
class IvOperationActivity : BaseActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    override fun getLayoutResource()=R.layout.view_activity_iv_operation

    override fun initView() {
        iv.scaleType= ImageView.ScaleType.MATRIX
    }

    override fun initListener() {
        btn.setOnClickListener(this)
        rg.setOnCheckedChangeListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            btn -> {
                val url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg"
//                 url = "http://p1.pstatp.com/large/166200019850062839d3"
//                val url = "http://172.16.0.103:8080/image/1.jpg"
                Glide.with(this)
                        .load(url)
                        .into(iv)
            }
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        Toast.makeText(this,checkedId.toString(), Toast.LENGTH_SHORT).show()
        when(checkedId){
            R.id.rbMatrix->{
                iv.scaleType= ImageView.ScaleType.MATRIX
                iv.imageMatrix= Matrix()
            }
            R.id.rbCenter->iv.scaleType=ImageView.ScaleType.CENTER
            R.id.rbCenterCrop->iv.scaleType=ImageView.ScaleType.CENTER_CROP
            R.id.rbCenterInside->iv.scaleType=ImageView.ScaleType.CENTER_INSIDE
            R.id.rbFitXY->iv.scaleType=ImageView.ScaleType.FIT_XY
            R.id.rbFitStart->iv.scaleType=ImageView.ScaleType.FIT_START
            R.id.rbFitCenter->iv.scaleType=ImageView.ScaleType.FIT_CENTER
            R.id.rbFitEnd->iv.scaleType=ImageView.ScaleType.FIT_END
        }
    }
}
