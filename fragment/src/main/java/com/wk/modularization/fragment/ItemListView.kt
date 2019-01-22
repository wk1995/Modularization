package com.wk.modularization.fragment

import android.content.Context
import android.util.AttributeSet
import android.widget.ArrayAdapter
import android.widget.ListView
import com.wk.modularization.fragment.ui.ViewReplaceFragmentActivity


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
class ItemListView(context: Context, attrs: AttributeSet) : ListView(context, attrs) {
    private val list by lazy {
        val list=ArrayList<String>()
        list.add("1")
        list.add("2")
        list.add("3")
        list.add("4")
        list
    }
    override fun onFinishInflate() {
        super.onFinishInflate()
        adapter=ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,list)
        setOnItemClickListener{
            _, _, position, _ ->
            val item = adapter.getItem(position) as String
            val activity = context as ViewReplaceFragmentActivity
            val container = activity.container
            container.showItem(item)
        }
    }

}