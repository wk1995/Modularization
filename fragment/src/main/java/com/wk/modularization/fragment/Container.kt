package com.wk.modularization.fragment

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2018/10/2018/10/11
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
interface Container {
    fun showItem(item: String)

    fun onBackPressed(): Boolean
}