package com.wk.context.activity

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath
import timber.log.Timber

@Route(path = RouterPath.ActivityMainActivity)
class ActivityMainActivity : BaseMainListActivity() {
    companion object {
        const val SET_RESULT = "沙翁\n" +
                "向昨天要经验； 向今天要结果； 向明天要动力\n" +
                "setResult()的调用时机"
    }

    override fun getRecyclerItemList(): List<String> {
        val list = ArrayList<String>()
        list.add(SET_RESULT)
        return list
    }

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val itemText = bundle?.getString(BundleKey.ItemText)
        when (itemText) {
            SET_RESULT -> {
                val intent = Intent(this@ActivityMainActivity, SecondActivity::class.java)
                startActivityForResult(intent, 2)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Timber.i("requestCode : $requestCode  resultCode: $resultCode")
    }


}
