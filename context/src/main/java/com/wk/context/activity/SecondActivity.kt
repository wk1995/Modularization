package com.wk.context.activity

import com.wk.common.base.BaseActivity
import com.wk.context.R
import kotlinx.android.synthetic.main.context_activity_second.*

/**
 * 主要是观察onActivityResult与Activity生命周期的时间关系
 *
 *
 * */
class SecondActivity : BaseActivity() {


    override fun getLayoutResource()=R.layout.context_activity_second
    override fun initView() {
        super.initView()
        btSecond.setOnClickListener {
            //一般都这样写
            setResult(3)
            finish()
        }
    }

    override fun onPause() {
        setResult(3)//无效
        super.onPause()
    }


}
