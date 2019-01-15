package com.wk.list.rv.simple

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wk.list.R

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2018/10/24
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
class SimpleOneItemAdapter(userList: List<User>)
    : BaseQuickAdapter<User, BaseViewHolder>(R.layout.list_item_brvah_test, userList) {

    override fun convert(helper: BaseViewHolder?, item: User?) {
        helper?.setText(R.id.tvUserAge, ""+item?.age)
                ?.setText(R.id.tvUserName, item?.name ?: "null")

    }

}