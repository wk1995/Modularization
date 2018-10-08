package com.wk.common.base

import android.support.constraint.ConstraintLayout
import android.view.View
import android.view.ViewStub
import android.widget.Button
import android.widget.TextView
import com.wk.common.R

/**
 * <pre>
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2018/09/17
 *      desc   :
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * </pre>
 */
abstract class BaseSimpleDialogFragment : BaseDialogFragment(), View.OnClickListener {
    protected lateinit var vsComSimpleDialogContent: ViewStub
    protected lateinit var btnComSimpleDialogOk: Button
    protected lateinit var tvComSimpleDialogTheme: TextView
    protected lateinit var btnComSimpleDialogCancel: Button
    final override fun getLayoutResource() = R.layout.common_dialog_fragment_base_simple

    override fun initView(rootView: View) {
        super.initView(rootView)
        vsComSimpleDialogContent = rootView.findViewById(R.id.vsComSimpleDialogContent)
        btnComSimpleDialogOk = rootView.findViewById(R.id.btnComSimpleDialogOk)
        tvComSimpleDialogTheme = rootView.findViewById(R.id.tvComSimpleDialogTheme)
        btnComSimpleDialogCancel = rootView.findViewById(R.id.btnComSimpleDialogCancel)
        vsComSimpleDialogContent.layoutResource=getViewSubLayout()
        val vsView=vsComSimpleDialogContent.inflate()
        val p= ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_CONSTRAINT,ConstraintLayout.LayoutParams.WRAP_CONTENT)
        p.topToBottom=R.id.tvComSimpleDialogTheme
        p.bottomToTop=R.id.btnComSimpleDialogOk
        p.leftToLeft=(vsView.parent as View).id
        p.rightToRight=(vsView.parent as View).id
        p.width=(vsView.parent as View).measuredWidth
        vsView.layoutParams=p
        (btnComSimpleDialogOk.layoutParams as ConstraintLayout.LayoutParams).topToBottom=vsView.id
        (btnComSimpleDialogCancel.layoutParams as ConstraintLayout.LayoutParams).topToBottom=vsView.id
        initVSView(vsView)


    }

    override fun initListener() {
        super.initListener()
        btnComSimpleDialogOk.setOnClickListener(this)
        btnComSimpleDialogCancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (dialog != null && dialog.isShowing)
            dismiss()
    }

    //加载ViewSub中的View
    abstract fun initVSView(vsView:View)

    //设置ViewSub被替换的布局
    abstract fun getViewSubLayout():Int
}