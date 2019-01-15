package com.wk.modularization.hardware.surface

import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.base.BaseActivity
import com.wk.common.constant.RouterPath
import com.wk.hotfix.R

@Route(path = RouterPath.SurfaceActivity)
class SurfaceActivity : BaseActivity() {

    override fun getLayoutResource()=R.layout.hardware_activity_surface


}
