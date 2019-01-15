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
data class LevelTwo(val title:String):AbstractExpandableItem<LevelThree>(),MultiItemEntity {
    override fun getItemType()=1
    override fun getLevel()=RvExpandAdapter.LevelTwo
}