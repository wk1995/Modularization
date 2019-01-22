package com.wk.retrofit.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.BundleKey
import com.wk.common.constant.RouterPath
import com.wk.retrofit.Body1
import com.wk.retrofit.RetrofitService
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

@Route(path = RouterPath.RetrofitMainActivity)
class RetrofitMainActivity : BaseMainListActivity() {
    companion object {
        const val rootUrl = "http://192.168.14.185:8080/wk/"
        const val Post="post"
        const val get="get"
    }

    private val retrofitOperationList by lazy {
        val retrofitOperationList = ArrayList<String>()
        retrofitOperationList.add("testByGet")
        retrofitOperationList.add("getAllAppName")
        retrofitOperationList.add("postBody")
        retrofitOperationList.add("postPath")
        retrofitOperationList.add("http")
        retrofitOperationList.add("postField")
        retrofitOperationList.add("postDynamicHeaders")
        retrofitOperationList.add("postStaticHeaders")
        retrofitOperationList.add("getBody")
        retrofitOperationList
    }
    private val okHttpClient by lazy {
        val cacheSize = 10 * 1024 * 1024L
        OkHttpClient
                .Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(Cache(externalCacheDir.absoluteFile, cacheSize))
                .build()
    }
    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(rootUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    override fun getRecyclerItemList() = retrofitOperationList

    override fun onRecyclerItemClick(bundle: Bundle?, vararg objects: Any?) {
        val operateName = bundle?.getString(BundleKey.ItemText) ?: return
        val retrofitService = retrofit.create(RetrofitService::class.java)
        var call:Call<out Any>?=null
        when (operateName) {
            retrofitOperationList[0] -> {
                 call = retrofitService.getTest()

            }
            retrofitOperationList[1] -> {
                 call = retrofitService.getAllAppName("apk")
            }
            retrofitOperationList[2] -> {
                call = retrofitService.postBody(Body1("wk",23))
            }
            retrofitOperationList[3] -> {
                call = retrofitService.postPath("test","apk")
            }
            retrofitOperationList[4] -> {
                call = retrofitService.http("test")
            }
            retrofitOperationList[5] -> {
                call = retrofitService.postField("apk")
            }
            retrofitOperationList[6] -> {
                call = retrofitService.postDynamicHeaders("apk")
            }
            retrofitOperationList[7] -> {
                call = retrofitService.postStaticHeaders()
            }
            retrofitOperationList[8] -> {
                call = retrofitService.getBody()
            }
        }

        way(call?:return)
    }

    private fun <T> way(call: Call<T>) {
        call.enqueue(object : retrofit2.Callback<T> {
            override fun onFailure(call: Call<T>?, t: Throwable?) {
                Timber.i(t?.message?:"null Throwable")
            }

            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                val t = response?.body()
                Timber.i(t.toString())
            }
        })
    }
}
