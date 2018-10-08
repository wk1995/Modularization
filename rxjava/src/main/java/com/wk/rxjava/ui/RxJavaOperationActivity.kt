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
import kotlinx.android.synthetic.main.rx_java_activity_creation.*
import rx.Observable
import rx.Subscription
import rx.functions.Action1
import timber.log.Timber
import java.util.*

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
        rvOperatorChoose.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
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
    }

    override fun onClick(v: View?) {
        when (v) {
            btnPlay -> {
                if (mSubscription?.isUnsubscribed == true) {
                } else {
                    mSubscription?.unsubscribe()
                    mSubscription = null
                }

                val methods = Reflect.way(this::class.java, "Operation")
                methods.forEach {
                    it.isAccessible = true
                    if (it.name == operationName) {
                        mObservable = (it.invoke(this) as Observable<out Any>?)
                        mSubscription = mObservable?.subscribe(
                                object : Action1<Any?> {
                                    override fun call(t: Any?) {
                                        Timber.i(" subscriber  ${Thread.currentThread().name}  onNext ${t?.toString()
                                                ?: "null"}")
                                    }
                                },
                                object : Action1<Throwable> {
                                    override fun call(t: Throwable?) {
                                        Timber.i(" subscriber  ${Thread.currentThread().name}  onError ${t?.message
                                                ?: "null"}")
                                    }
                                }
                        )
                        return
                    }
                }
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