package com.wk.modularization.web

import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.wk.common.base.BaseActivity
import com.wk.view.R
import kotlinx.android.synthetic.main.web_activity_main.*

class WebMainActivity : BaseActivity(), View.OnClickListener {

    private var webView: WebView? = null

    override fun getLayoutResource() =-1

    override fun initView() {
        super.initView()
        webView = WebView(this.applicationContext)
        setContentView(webView)
        webView?.loadUrl("https://www.baidu.com/")

    }

    override fun initListener() {
        super.initListener()
    }

    override fun initParam() {
        super.initParam()
    }

    override fun onClick(v: View?) {
        when (v) {
            btSearch -> {
                val webSite = etWebsite.text.toString().trim()
                webView?.loadUrl(webSite)
            }
        }
    }

    override fun onDestroy() {
        if (webView != null) {
            webView?.loadDataWithBaseURL(null, "", "text/html", "utf-8", null)
            webView?.clearHistory()
            (webView?.parent as? ViewGroup)?.removeAllViews()
            webView?.destroy()
            webView = null
        }
        super.onDestroy()

    }
}
