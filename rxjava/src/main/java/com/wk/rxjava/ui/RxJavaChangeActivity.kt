package com.wk.rxjava.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.constant.RouterPath
import com.wk.rxjava.Operation
import rx.Observable
import kotlin.collections.ArrayList

/**
 * <pre>
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2018/08/30
 *      desc   : 变换操作符
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * </pre>
 */
@Route(path = RouterPath.RxJavaChangeActivity)
class RxJavaChangeActivity : RxJavaOperationActivity() {
    override fun getOperators(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("map")
        list.add("flatMap")
        list.add("concatMap")
        list.add("cast")
        list.add("flatMapIterable")
        list.add("buffer")
        list.add("groupBy")
        list.add("window")
        list.add("scan")
        list.add("toList")
        return list
    }

    @Operation
    private fun toList() {
        Observable.just("just1", "just2", "just3", "just4")
                .toList()
    }


    @Operation
    private fun scan() {
        Observable.just("just1", "just2", "just3", "just4")
                .scan { t1, t2 ->
                    "t1: $t1 + t2  $t2"
                }
    }

    @Operation
    private fun window() {
        Observable.just("just1", "just2", "just3", "just4")
                .window(2)
    }

    @Operation
    private fun groupBy() {
        Observable.just(1, 2, 3, 4, 1, 2, 3, 4)
                .groupBy {
                    it
                }
    }


    @Operation
    private fun buffer() {
        Observable.just("just1", "just2", "just3", "just4").buffer(2)
    }


    @Operation
    private fun flatMapIterable() {
        Observable
                .just("just1", "just2", "just3")
                .flatMapIterable {
                    val list = ArrayList<String>()
                    list.add("$it flatMapIterable")
                    list
                }
    }


    @Operation
    private fun cast() =
            Observable.just(1, 2).cast(String::class.java)

    @Operation
    private fun map() =
            Observable.just("just1", "just2").map {
                it + "map"
            }


    @Operation
    private fun flatMap() =
            Observable
                    .just("just1", "just2", "just3", "just4", "just5")
                    .flatMap {
                        Observable.just("$it flatMap")
                    }

    @Operation
    private fun concatMap() =
            Observable
                    .just("just1", "just2")
                    .concatMap {
                        Observable.just("$it concatMap")
                    }
}

