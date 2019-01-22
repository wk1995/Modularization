package com.wk.animation.view

import android.os.Bundle
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey

class ViewAMainActivity : BaseMainListActivity() {

    companion object {
        private const val ALPHA = "alpha"
        private const val SCALE = "scale"
    }


    override fun getRecyclerItemList(): List<String> {
        val list = ArrayList<String>()
        list.add(ALPHA)
        list.add(SCALE)
        return list
    }

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val itemText = bundle?.getString(BundleKey.ItemText)
        when (itemText) {
            ALPHA -> {
            }
            SCALE -> {
            }
        }
    }
}
