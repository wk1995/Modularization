package com.wk.list.rv.expand

import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2018/10/28
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
data class LevelOne(val title:String):AbstractExpandableItem<LevelTwo>(),MultiItemEntity {

    override fun getLevel()=0

    override fun getItemType()=RvExpandAdapter.LevelOne
}