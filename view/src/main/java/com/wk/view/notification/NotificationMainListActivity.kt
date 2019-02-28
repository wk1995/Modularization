package com.wk.view.notification

import com.wk.common.base.BaseMainListActivity

class NotificationMainListActivity : BaseMainListActivity() {


    override fun dealRecyclerItemClick(itemText: String?) {
        super.dealRecyclerItemClick(itemText)
        when (itemText) {

        }
    }

    override fun getRecyclerItemList(): List<String> {
        val list=ArrayList<String>()
        return list
    }

}
