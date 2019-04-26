package com.wk.rxjava.ui

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.wk.common.base.BaseActivity
import com.wk.common.constant.BundleKey
import com.wk.common.transmission.RecyclerItemListener
import com.wk.rxjava.MyRecyclerViewAdapter
import com.wk.rxjava.R
import com.wk.rxjava.Reflect
import com.wk.rxjava.SomeType
import kotlinx.android.synthetic.main.rx_java_activity_creation.*
import rx.Observable
import rx.Subscriber
import rx.Subscription
import rx.functions.Func0
import timber.log.Timber

/**
 * <pre>
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2018/08/30
 *      desc   :
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * </pre>
 */
abstract class RxJavaOperationActivity : BaseActivity(), View.OnClickListener, RecyclerItemListener {
    private var mObservable: Observable<out Any>? = null
    private var mSubscription: Subscription? = null
    private var operationName: String? = null
    private val myRecyclerViewAdapter by lazy {
        MyRecyclerViewAdapter(getOperators(), this, 1)
    }

    override fun getLayoutResource() = R.layout.rx_java_activity_creation

    override fun initView() {
        rvOperatorChoose.layoutManager = LinearLayoutManager(this)
        rvOperatorChoose.adapter = myRecyclerViewAdapter
        //设置分割线
        rvOperatorChoose.addItemDecoration(DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL))
    }

    override fun initParam() {
        operationName = myRecyclerViewAdapter.getSelected()
    }

    override fun initListener() {
        btnPlay.setOnClickListener(this)
        btnPlay1.setOnClickListener(this)
        /* Observable.create<String> {
             Timber.i("create:  ${Thread.currentThread().name}")
             it?.onNext("create")
         }.subscribeOn(AndroidSchedulers.mainThread())
                 .observeOn(Schedulers.newThread())
                 .subscribe {
                     Timber.i(" onNext  ${Thread.currentThread().name}   $it")
                 }*/

    }

    override fun onClick(v: View?) {

        when (v) {
            btnPlay -> {
                if (mSubscription?.isUnsubscribed == false) {
                    mSubscription?.unsubscribe()
                    mSubscription = null
                }

                val methods = Reflect.way(this::class.java, "Operation")
                methods.forEach {
                    it.isAccessible = true
                    if (it.name == operationName) {
                        mObservable = (it.invoke(this) as Observable<out Any>?)
                        mSubscription = mObservable?.subscribe(object : Subscriber<Any?>() {
                            override fun onNext(t: Any?) {
                                Timber.i(" Subscriber  onNext  ${Thread.currentThread().name}   ${t?.toString()
                                        ?: "null"}")
                            }

                            override fun onCompleted() {
                            }

                            override fun onError(e: Throwable?) {
                                Timber.i(" Subscriber  onError ${Thread.currentThread().name}   ${e?.message
                                        ?: "null"}")
                            }
                        })
                        return
                    }
                }
            }
            btnPlay1 -> {
               /* val mSomeType = SomeType()
                val observable1 = mSomeType.valueJustObservable()
                val observable2 = mSomeType.valueDeferObservable()
                val observable3 = mSomeType.valueCreateObservable()
                mSomeType.setValue("wk")
                observable1.subscribe(System.out::println)
                observable2.subscribe(System.out::println)
                observable3.subscribe(System.out::println)*/
                var target = "just1"
                val result1 = Observable.just(target)
                val result2 = Observable.defer{
                     Observable.just(target)
                }
                target = "just2"
                result1.subscribe(System.out::println)
                result2.subscribe(System.out::println)
            }
        }
    }

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        operationName = bundle?.getString(BundleKey.ItemText) ?: return
        myRecyclerViewAdapter.notifyDataSetChanged()
    }

    abstract fun getOperators(): ArrayList<String>
    override fun onPause() {
        super.onPause()
        if (mSubscription?.isUnsubscribed == true) {
        } else {
            mSubscription?.unsubscribe()
            mSubscription = null
        }
    }
}