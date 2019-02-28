package com.wk.view.remote

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.alibaba.android.arouter.facade.annotation.Route
import com.wk.common.base.BaseMainListActivity
import com.wk.common.constant.RouterPath
import com.wk.view.R

@Route(path = RouterPath.RemoteMainListActivity)
class RemoteMainListActivity : BaseMainListActivity() {
    private val intent1 by lazy { Intent(this,RemoteMainListActivity::class.java) }
    private val pendingIntent by lazy {
        PendingIntent.getActivity(this,0,intent1,PendingIntent.FLAG_ONE_SHOT)
    }

    companion object {
        const val NOTIFICATION = "自定义通知栏"
    }


    override fun getRecyclerItemList(): List<String> {
        val list = ArrayList<String>()
        list.add(NOTIFICATION)
        return list
    }



    override fun dealRecyclerItemClick(itemText: String?) {
        when(itemText){
            NOTIFICATION->{
                val notification=Notification()
                notification.icon= R.drawable.ic_launcher_background
                notification.tickerText="Hello World"
                notification.`when`=System.currentTimeMillis()
                notification.flags=Notification.FLAG_AUTO_CANCEL
                val remoteViews=RemoteViews(packageName,R.layout.view_remote_layout)
                remoteViews.setTextViewText(R.id.tvRemoteLayout,"wk")
                remoteViews.setImageViewResource(R.id.ivRemoteLayout,R.drawable.ic_launcher_background)
                val openOtherPendingIntent=PendingIntent.getActivity(
                        this,0,
                        Intent(this,RemoteEmptyActivity::class.java)
                        ,PendingIntent.FLAG_ONE_SHOT)
                remoteViews.setOnClickPendingIntent(R.id.llRemoteLayout,openOtherPendingIntent)
                notification.contentView=remoteViews
                notification.contentIntent=pendingIntent
                val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.notify(2,notification)
            }
        }
    }
}
