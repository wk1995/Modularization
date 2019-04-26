package com.wk.rxjava

import rx.Observable
import rx.Subscriber
import rx.functions.Func0

/**
 * <pre>
 * author : wk
 * e-mail : 122642603@qq.com
 * time   : 2019/4/17
 * GitHub : https://github.com/wk1995
 * CSDN   : http://blog.csdn.net/qq_33882671
 * desc   :
</pre> *
 */
class SomeType {
    private var value: String? = null

    fun setValue(value: String) {
        this.value = value
    }

    fun valueDeferObservable(): Observable<String> {
        return Observable.defer { Observable.just("defer $value" ) }
    }

    fun valueJustObservable(): Observable<String> {
        return Observable.just("just $value")
    }

    fun valueCreateObservable(): Observable<String> {
        return Observable.create { subscriber ->
            subscriber.onNext("create $value")
            subscriber.onCompleted()
        }
    }
}
