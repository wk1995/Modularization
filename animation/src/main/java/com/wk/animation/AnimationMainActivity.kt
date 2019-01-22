package com.wk.animation

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2019/1/16
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
@Route(path = RouterPath.AnimationMainActivity)
class AnimationMainActivity : BaseMainListActivity() {

    companion object {
        private const val VIEW_ANIMATION = "View动画"
    }

    override fun getRecyclerItemList(): List<String> {
        val list = ArrayList<String>()
        list.add(VIEW_ANIMATION)
        return list
    }

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val itemText = bundle?.getString(BundleKey.ItemText)
        when (itemText) {
            VIEW_ANIMATION -> {
            }
        }
    }
}