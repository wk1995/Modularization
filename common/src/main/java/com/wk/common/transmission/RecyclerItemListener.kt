package com.wk.common.transmission

import android.os.Bundle

/**
 * <pre>
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2018/08/22
 *      desc   :
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * </pre>
 */
interface RecyclerItemListener {

    /**
     * bundle: key=ItemPosition:item的位置
     *         key=ItemText    :item所显示的text
     * */
    fun onRecyclerItemClick(bundle: Bundle?=null,vararg objects:Any?)
}