package com.wk.rxjava.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.constant.RouterPath
import com.wk.rxjava.Operation
import rx.Observable
import java.util.*
import java.util.concurrent.TimeUnit

@Route(path = RouterPath.RxJavaCreationActivity)
class RxJavaCreationActivity : RxJavaOperationActivity() {

    override fun getOperators(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("defer")
        list.add("range")
        list.add("interval")
        list.add("repeat")
        list.add("timer")
        list.add("create")
        list.add("just")
        list.add("from")
        return list
    }

    @Operation
    private fun create()=
         Observable.create<String> {
            it?.onNext("create")
        }


    @Operation
    private fun just()=
       Observable.just("just1", "just2")


    @Operation
    private fun from(): Observable<out Any> {
        val list = ArrayList<String>()
        list.add("from1")
        list.add("from2")
        return Observable.from(list)

    }

    @Operation
    private fun timer()=
       Observable.timer(1000, TimeUnit.MILLISECONDS)


    @Operation
    private fun repeat()=
         Observable.range(0, 10).repeat(3)




    @Operation
    private fun interval()=
        Observable.interval(1000L,TimeUnit.MILLISECONDS)


    @Operation
    private fun range()=
        Observable.range(0,100)


    @Operation
    private fun defer()=
        Observable.defer{
            Observable.just("defer")
        }

}
