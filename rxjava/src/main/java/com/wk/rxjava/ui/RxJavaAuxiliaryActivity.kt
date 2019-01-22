package com.wk.rxjava.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.constant.RouterPath
import com.wk.rxjava.Operation
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import timber.log.Timber

@Route(path = RouterPath.RxJavaAuxiliaryActivity)
class RxJavaAuxiliaryActivity : RxJavaOperationActivity() {
    private val operationList by lazy {
        val operationList = ArrayList<String>()
        operationList.add("delay")
        operationList.add("do")
        operationList.add("subscribeObserveOn")
        operationList
    }

    override fun getOperators() = operationList

    @Operation
    fun subscribeObserveOn() =
            Observable.create<String> {
                Timber.i("create:  ${Thread.currentThread().name}")
                it.onNext("create")
            }
                    .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())

}
