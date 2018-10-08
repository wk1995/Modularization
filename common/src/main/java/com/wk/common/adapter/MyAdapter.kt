package com.wk.common.adapter

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wk.common.constant.BundleKey
import com.wk.common.transmission.RecyclerItemListener

/**
 * <pre>
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2018/08/24
 *      desc   :
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * </pre>
 */
class MyAdapter(private val list:List<String>,
                private val recyclerItemListener: RecyclerItemListener,
                private val type:Int=0 ): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    class MyViewHolder( val v: View): RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        val v= LayoutInflater
                .from(parent.context)
                .inflate(
                        if(type==0)
                            android.R.layout.simple_list_item_1
                        else
                            android.R.layout.simple_list_item_single_choice
                        ,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount()=list.size
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        (holder.v as TextView).text=list[position]
        val bundle=Bundle(1)
        bundle.putInt(BundleKey.ItemPosition,position)
        bundle.putString(BundleKey.ItemText,list[position])
        holder.v.setOnClickListener {
            recyclerItemListener.onRecyclerItemClick(bundle)
        }
    }
}