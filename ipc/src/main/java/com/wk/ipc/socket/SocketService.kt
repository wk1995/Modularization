package com.wk.ipc.socket

import android.app.Service
import android.content.Intent
import android.os.IBinder
import timber.log.Timber
import java.io.*
import java.net.ServerSocket
import java.net.Socket

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2019/1/6
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
class SocketService : Service() {

    private var isServiceDestory = false
    override fun onBind(intent: Intent?): IBinder? = null
    override fun onCreate() {
        isServiceDestory = false
        Thread(SocketThread()).start()
        super.onCreate()
    }

    inner class SocketThread : Runnable {
        override fun run() {
            var mServerSocket: ServerSocket?
            try {
                mServerSocket = ServerSocket(8888)
            } catch (e: IOException) {
                Timber.e("establish tcp server failed port :8888 ")
                e.printStackTrace()
                return
            }
            while (!isServiceDestory){
                try {

                    val socket=mServerSocket.accept()
                    Timber.i("accept")
                    Thread{

                    }.start()


                }catch (e:IOException){

                }
            }

        }
    }

    private fun responseClient(socket: Socket){
        //接受的
        val br=BufferedReader(InputStreamReader(socket.getInputStream()))
        //寫入
        val pw=PrintWriter(OutputStreamWriter(socket.getOutputStream()))
        pw.println("欢迎来到聊天室：")
        while(!isServiceDestory){
            val readLine=br.readLine()
            Timber.i("Service 收到的消息是： $readLine")
            if(readLine==null) {
                break
            }
            pw.println("收到客户端消息： $readLine")

        }
        Timber.i("客户端断开")
        pw.close()
        br.close()
        socket.close()
    }
}