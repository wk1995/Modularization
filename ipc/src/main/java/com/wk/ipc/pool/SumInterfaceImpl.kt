package com.wk.ipc.pool

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2019/1/14
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
class SumInterfaceImpl:ISumInterface.Stub() {
    override fun add(a: Int, b: Int)=a+b
}