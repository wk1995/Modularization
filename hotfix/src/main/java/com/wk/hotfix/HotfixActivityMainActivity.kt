package com.wk.hotfix

import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.base.BaseActivity
import com.wk.common.constant.RouterPath

@Route(path = RouterPath.HotfixActivityMainActivity)
class HotfixActivityMainActivity : BaseActivity() {
    override fun getLayoutResource()=R.layout.hotfix_activity_main

    override fun initView() {
    }

    override fun initListener() {
    }


}
