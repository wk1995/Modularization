package com.wk.common

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import timber.log.Timber



/**
 * <pre>
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2018/08/19
 *      desc   :
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * </pre>
 */
open class BaseApplication:Application() {

    override fun onCreate() {

        initRouter()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
         else {
            Timber.plant(object :Timber.Tree(){
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                }
            })
        }

        super.onCreate()
    }

    private fun initRouter(){
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog()
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this)
    }

}