package com.wk.list.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * <pre>
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2018/09/10
 *      desc   :
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * </pre>
 */
public class PullFlushListView extends ListView {
    public PullFlushListView(Context context) {
        this(context,null);
    }

    public PullFlushListView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public PullFlushListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
