package com.wk.list.rv.expand

import android.content.ClipData
import android.widget.CheckBox
import android.widget.TextView
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.wk.list.R
import timber.log.Timber

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
class RvExpandAdapter(val data:ArrayList<MultiItemEntity>):BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder>(data) {

    companion object {
        const val LevelOne=0
        const val LevelTwo=1
        const val LevelThree=2

    }

    init {
        addItemType(LevelOne, R.layout.level_one)
        addItemType(LevelTwo,R.layout.level_two)
        addItemType(LevelThree,R.layout.level_three)
    }

    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {

        when(item?.itemType){
            LevelOne->{
                val levelOne=item as LevelOne
                helper?.setText(R.id.tv_level_one,levelOne.title)
                helper?.getView<TextView>(R.id.tv_level_one)?.setOnClickListener {
                    val position=helper.adapterPosition
                    Timber.d("adapterPosition:  $position  layoutPosition ${helper.layoutPosition}")
                    if(levelOne.isExpanded)
                        collapse(position)
                    else
                        expand(position)
                }

            }
            LevelTwo->{
                val levelTwo=item as LevelTwo
                helper?.setText(R.id.tv_level_two,levelTwo.title)
                helper?.getView<TextView>(R.id.tv_level_two)?.setOnClickListener {
                    val position=helper.adapterPosition
                    Timber.d("adapterPosition:  $position  layoutPosition ${helper.layoutPosition}")
                    if(levelTwo.isExpanded)
                        collapse(position)
                    else
                        expand(position)
                }
            }
            LevelThree->{
                val levelThree=item as LevelThree
                helper?.setText(R.id.tv_level_three,levelThree.title)
                helper?.setText(R.id.tv_level_three_desc, levelThree.desc)
                helper?.getView<CheckBox>(R.id.cb_three)?.setOnClickListener {
                    levelThree.isCheck=!levelThree.isCheck
                    Timber.d(if(levelThree.isCheck) "open" else "close")
                }
            }
        }
    }
}