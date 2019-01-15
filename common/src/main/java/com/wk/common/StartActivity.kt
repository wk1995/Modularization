package com.wk.common

import android.app.Activity
import android.content.Intent

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2018/10/23
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
object StartActivity {

    fun setLauncher(activity:Activity){
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.setClassName("android",
                "com.android.internal.app.ResolverActivity")
        activity.startActivity(intent)
    }
}