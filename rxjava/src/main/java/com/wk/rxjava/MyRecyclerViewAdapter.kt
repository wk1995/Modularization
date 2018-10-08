package com.wk.rxjava

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import com.wk.common.constant.BundleKey
import com.wk.common.transmission.RecyclerItemListener

/**
 * <pre>
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2018/08/27
 *      desc   :
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * </pre>
 */
class MyRecyclerViewAdapter(private val nameList:ArrayList<String>,
                            private val mRecyclerItemListener: RecyclerItemListener,
                            private val type:Int=0)
    : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>(){
    class MyViewHolder(val v: View): RecyclerView.ViewHolder(v)


    private val map by lazy {
        val map=HashMap<String,Boolean>()
        nameList.forEach {
            map[it]=false
        }
        map[nameList[0]]=true
        map
    }
    private var oldChoose:String?=null
    private var nowChoose=nameList[0]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=
                LayoutInflater.from(parent.context)
                        .inflate(if(type==0) R.layout.rx_java_list_types else R.layout.rx_java_list_item_operator_choose
                                ,parent,false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if(holder.v is TextView)
            holder.v.text=nameList[position]
        if(holder.v is RadioButton)
            holder.v.text=nameList[position]
        val bundle=Bundle()
        bundle.putString(BundleKey.ItemText,nameList[position])
        holder.v.setOnClickListener {
            mRecyclerItemListener.onRecyclerItemClick(bundle)
            oldChoose=nowChoose
            if(oldChoose!=null)
                map[oldChoose!!]=false
            nowChoose=nameList[position]
            map[nowChoose]=true

        }
        if(holder.v is RadioButton)
            holder.v.isChecked=map[nameList[position]]?:false
    }

    override fun getItemCount()=nameList.size

    fun getSelected():String?{
        for((k,v) in map){
            if(v)
                return k

        }
        return null
    }
}