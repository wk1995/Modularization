package com.wk.common.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.wk.common.base.BaseActivity

/**
 * <pre>
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2018/08/20
 *      desc   :
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * </pre>
 */
abstract class BaseDialogFragment:DialogFragment() {
    lateinit var mContext: Context
//    lateinit var mActivity: BaseActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
//        mActivity=context as BaseActivity
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        hideBottomUIMenu()
        val rootView=inflater.inflate(getLayoutResource(),container,false)
        initView(rootView)
        initParameter()
        initListener()
        return rootView
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, 0)
    }

    /**
     * 隐藏虚拟键
     */
    private fun hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        val v = dialog.window.decorView
        if (Build.VERSION.SDK_INT in 11 .. 18) { // lower api

            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.

            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN)
            v.systemUiVisibility = uiOptions

        }

    }


    abstract fun getLayoutResource():Int
    //初始化View
    open fun initView(rootView:View){}
    //设置监听
    open fun initListener(){}
    open fun initParameter(){}

    fun show( manager: FragmentManager){
        show(manager,this::class.java.simpleName)
    }
}