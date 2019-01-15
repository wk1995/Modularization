package com.wk.list.rv.expand

import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.wk.common.constant.RouterPath
import com.wk.list.rv.BaseRvBrvahActivity
import kotlinx.android.synthetic.main.list_activity_rv_brvah.*


@Route(path = RouterPath.RvExpandActivity)
class RvExpandActivity : BaseRvBrvahActivity() {

    override fun initView() {
        super.initView()
        val list=getMultiList()
        rvBTest.layoutManager=LinearLayoutManager(this)
        val adapter=RvExpandAdapter(list)
        rvBTest.adapter=adapter
        // 使一级列表默认展开,但用下面的方法就会有问题
        for (i in list.indices.reversed()) {
            adapter.expand(i, false, false)
        }
/*        for(i in 0 until list.size)
            adapter.expand(i,false,false)*/

    }

    private fun getMultiList():ArrayList<MultiItemEntity>{
        val one=10
        val two=3
        val multiList=ArrayList<MultiItemEntity>()
        for(i in 0 until one){
            val levelOne=LevelOne("一级标题$i")
            for(j in 0 until two){
                val levelTwo=LevelTwo("二级标题$j")
                levelTwo.addSubItem(LevelThree("三级标题","这是个例子哦",false))
                levelOne.addSubItem(levelTwo)
            }
            multiList.add(levelOne)
        }
        return multiList
    }
}
