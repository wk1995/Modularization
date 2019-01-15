package com.wk.okhttp

import android.os.Environment
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.base.BaseActivity
import com.wk.common.constant.RouterPath
import kotlinx.android.synthetic.main.okhttp_activity_main.*
import okhttp3.*
import okio.BufferedSink
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.io.PrintWriter

@Route(path = RouterPath.OkHttpMainActivity)
class OkHttpMainActivity : BaseActivity(), View.OnClickListener {
    companion object {
        private const val rootUrl = "http://192.168.14.185:8080/wk"
        private const val queryUrl = "$rootUrl/query"
        private const val downLoadUrl = "$rootUrl/downLoad"
        private const val testUrl = "$rootUrl/test"
    }


    private val client by lazy { OkHttpClient.Builder().build() }

    override fun onClick(v: View?) {
        var request: Request? = null
        when (v) {
            btnMainActivityOkHttpGet -> {
                request = Request.Builder()
                        .get().url("https://blog.csdn.net/zhangphil/article/details/48155371")
                        .build()
//                TextSimpleDialog.getInstance().show(supportFragmentManager)
            }
            btnMainActivityOkHttpPost -> {
                val requestBody = getRequestBody(rg.checkedRadioButtonId)
                if (requestBody == null) {
                    Timber.e("requestBody is null")
                    return
                }
                request=Request.Builder()
                        .url(testUrl)
                        .post(requestBody)
                        .build()
            }
        }
        if(request==null) return
        val call = client.newCall(request)
        when (rgAsy.checkedRadioButtonId) {
            R.id.rbAsy -> {
                call.enqueue(object : Callback {
                    override fun onFailure(call: Call?, e: IOException?) {
                        Timber.i("get fail")
                    }

                    override fun onResponse(call: Call?, response: Response?) {
                        Timber.i("get success")
                        val string = response?.body()?.string() ?: "null"
                        runOnUiThread {
                            tv.text = string
                        }
                    }

                })
            }
            R.id.rbSyn -> call.execute()
        }

    }

    private fun getRequestBody(type: Int): RequestBody? {
        var requestBody: RequestBody? = null
        when (type) {
            R.id.rbSoap -> {
                val soap = MediaType.parse("text/xml; charset=utf-8")
            }
            R.id.rbUpLoadFile -> {
                val mediaTypeMarkdown = MediaType.parse("text/x-markdown; charset=utf-8")
                val path = Environment.getExternalStorageDirectory().absolutePath + "wk/rbUpLoadFile.txt"
                val upLoadFile = File(path)
                if (!upLoadFile.exists())
                    if (upLoadFile.parentFile.mkdirs())
                        upLoadFile.createNewFile()
                val pw = PrintWriter(upLoadFile)
                pw.write("wk:upLoadFile")
                pw.flush()
                pw.close()
                requestBody = RequestBody.create(mediaTypeMarkdown, upLoadFile)

            }
            R.id.rbDownloadFile -> {

            }


            R.id.rbForm -> {
                requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("type", "apk")
                        .build()

            }
            R.id.rbKeyValue -> {
                requestBody = FormBody.Builder().add("type", "apk")
                        .build()
            }
            R.id.rbStream -> {
                val media = MediaType.parse("text/x-markdown; charset=utf-8")?:return null
                requestBody = object : RequestBody() {
                    override fun contentType(): MediaType {
                        return media
                    }

                    @Throws(IOException::class)
                    override fun writeTo(sink: BufferedSink) {
                        sink.writeUtf8("Numbers\n")
                        sink.writeUtf8("-------\n")
                        for (i in 2..997) {
                            sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)))
                        }
                    }

                    private fun factor(n: Int): String {
                        for (i in 2 until n) {
                            val x = n / i
                            if (x * i == n) return factor(x) + " × " + i
                        }
                        return Integer.toString(n)
                    }
                }


            }
            R.id.rbString -> {
                requestBody = RequestBody
                        .create(
                                MediaType.parse("application/xml"),"type=apk")
            }
        }
        return requestBody
    }

    override fun getLayoutResource() = R.layout.okhttp_activity_main
    override fun initListener() {
        btnMainActivityOkHttpGet.setOnClickListener(this)
        btnMainActivityOkHttpPost.setOnClickListener(this)
    }
}
