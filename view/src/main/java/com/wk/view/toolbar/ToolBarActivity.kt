package com.wk.view.toolbar

import android.support.v7.view.menu.MenuBuilder
import android.view.Menu
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.base.BaseActivity
import com.wk.common.constant.RouterPath
import com.wk.common.widget.toast.ToastUntil
import com.wk.view.R
import kotlinx.android.synthetic.main.view_activity_tool_bar.*

@Route(path = RouterPath.ToolBarActivity)
class ToolBarActivity : BaseActivity() {


    override fun getLayoutResource() = R.layout.view_activity_tool_bar

    override fun initView() {
        super.initView()
        if (supportActionBar != null)
            supportActionBar?.hide()
        toolbar.setLogo(R.drawable.ic_launcher_background)
        toolbar.title = "Title"
        toolbar.subtitle = "sub Title"
        toolbar.setNavigationIcon(R.drawable.close)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            ToastUntil.showToast("Navigation", this)
        }
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item1 ->
                    ToastUntil.showToast("item1", this)
                R.id.item2 ->
                    ToastUntil.showToast("item2", this)
                R.id.item3 ->
                    ToastUntil.showToast("item3", this)
                R.id.item4 ->
                    ToastUntil.showToast("item4", this)
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    //显示menu的图标
    override fun onPrepareOptionsPanel(view: View?, menu: Menu?): Boolean {
        if (menu is MenuBuilder)
            menu.setOptionalIconsVisible(true)

        return super.onPrepareOptionsPanel(view, menu)
    }
}
