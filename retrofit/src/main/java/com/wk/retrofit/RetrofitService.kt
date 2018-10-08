package com.wk.retrofit

import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.Body

/**
 * <pre>
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2018/09/24
 *      desc   :
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * </pre>
 */
interface RetrofitService {

    @GET("test")
    fun getTest(): Call<String>

    @GET("test")
    fun getAllAppName(@Query("type") type: String): Call<String>

    @GET("{path}")
    fun getPath(@Path("path") path: String): Call<String>


    //轉化為Json
    @POST("test")
    fun postBody(@Body body: com.wk.retrofit.Body1): Call<String>

    @POST("{operate}")
    fun postPath(@Path("operate") operate: String, @Query("type") type: String): Call<String>


    @HTTP(method = "post", path = "{path}", hasBody = false)
    fun http(@Path("path")path:String): Call<String>

    @FormUrlEncoded
    @POST("test")
    fun postField(@Field("type") type:String): Call<String>


    @POST("test")
    fun postDynamicHeaders(@Header("Location") Location:String): Call<String>

    @POST("test")
    @Headers(
        "Accept-Encoding:application/json",
        "User-Agent: MoonRetrofit"
    )
    fun postStaticHeaders(): Call<String>

    @POST("test")
    fun getBody(): Call<Body1>


}