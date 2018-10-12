package com.wk.modularization.fragment

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.wk.list.R


/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2018/10/2018/10/11
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
class SinglePaneContainer(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs), Container {
    private var listView: ItemListView? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        listView = getChildAt(0) as ItemListView
    }

    override fun onBackPressed(): Boolean {
        if (!listViewAttached()) {
            removeViewAt(0)
            addView(listView)
            return true
        }
        return false
    }

    override fun showItem(item: String) {
        if (listViewAttached()) {
            removeViewAt(0)
            View.inflate(context, R.layout.detail, this)
        }

    }

    private fun listViewAttached(): Boolean {
        return listView!!.parent != null
    }
}