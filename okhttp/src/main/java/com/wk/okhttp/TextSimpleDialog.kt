package com.wk.okhttp

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.wk.common.base.BaseSimpleDialogFragment

/**
 * <pre>
 * author : wk
 * e-mail : 1226426603@qq.com
 * time   : 2018/09/17
 * desc   :
 * GitHub : https://github.com/wk1995
 * CSDN   : http://blog.csdn.net/qq_33882671
</pre> *
 */
class TextSimpleDialog : BaseSimpleDialogFragment() {
    val list by lazy {
        val list=ArrayList<String>()
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list
    }
    companion object {
        fun getInstance() = TextSimpleDialog()

    }

    override fun initVSView(vsView: View) {
        val rv = vsView.findViewById<RecyclerView>(R.id.rv)
        rv.adapter=AppListAdapter(list)
        rv.layoutManager=LinearLayoutManager(mContext)
    }

    override fun getViewSubLayout() =R.layout.okhttp_rv


    class AppListAdapter(
            private val appsList:List<String>
    )
        :RecyclerView.Adapter<AppListAdapter.AppListViewHolder>(){
        class AppListViewHolder(v:View,val tvAppName:TextView,val btDownLoadApp:Button):RecyclerView.ViewHolder(v)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppListViewHolder {
            val rootView=LayoutInflater.from(parent.context).inflate(R.layout.okhttp_tv,parent,false)
            val tvAppName=rootView.findViewById(R.id.tvAppName) as TextView
            val btDownLoadApp=rootView.findViewById(R.id.btDownLoadApp) as Button
            return AppListViewHolder(rootView,tvAppName,btDownLoadApp)
        }

        override fun onBindViewHolder(holder: AppListViewHolder, position: Int) {
            val appName=appsList[position]
            holder.tvAppName.text=appName

        }

        override fun getItemCount()=appsList.size
    }
}
