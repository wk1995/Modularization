package com.wk.common.base

import android.content.Context
import android.support.v4.app.Fragment
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
abstract class BaseFragment: Fragment() {

    lateinit var mContext: Context
    lateinit var mActivity: BaseActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext=context
        mActivity=context as BaseActivity
    }

}