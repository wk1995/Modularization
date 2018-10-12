package com.wk.modularization.fragment.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath

/**
 * <pre>
 * author : wk
 * e-mail : 122642603@qq.com
 * time   : 2018/10/11
 * GitHub : https://github.com/wk1995
 * CSDN   : http://blog.csdn.net/qq_33882671
 * desc   :
</pre> *
 */

@Route(path = RouterPath.FragmentMainListActivity)
class FragmentMainListActivity : BaseMainListActivity(){
    private val listItem by lazy {
        val listItem =ArrayList<String>()
        listItem.add("View替代Fragment")
        listItem
    }
    override fun getRecyclerItemList()=listItem

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val position=bundle?.getInt(BundleKey.ItemPosition,-1)?:return
        when(position){
            1->ARouter.getInstance().build(RouterPath.ViewReplaceFragmentActivity).navigation()
        }

    }
}
