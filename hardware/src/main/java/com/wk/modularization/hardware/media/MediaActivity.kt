package com.wk.modularization.hardware.media

import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.base.BaseActivity
import com.wk.common.constant.RouterPath
import com.wk.hotfix.R

@Route(path = RouterPath.MediaActivity)
class MediaActivity : BaseActivity() {


    override fun getLayoutResource()= R.layout.hardware_activity_media

    override fun initView() {
        super.initView()
    }

    override fun initListener() {
        super.initListener()
    }
}
