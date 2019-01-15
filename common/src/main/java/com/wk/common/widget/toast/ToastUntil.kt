package com.wk.common.widget.toast

import android.content.Context
import android.widget.Toast

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2018/12/28
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
object ToastUntil {

    fun showToast(msg:String,context: Context){
        Toast.makeText(context.applicationContext,msg,Toast.LENGTH_SHORT).show()
    }
}