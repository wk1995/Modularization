package com.wk.view.remote

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.SystemClock
import android.widget.RemoteViews
import android.widget.Toast
import com.wk.common.widget.toast.ToastUntil

import com.wk.view.R
import timber.log.Timber

/**
 * Implementation of App Widget functionality.
 */
class MyAppWidgetProvider : AppWidgetProvider() {

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        when(intent?.action){
            CLICK_ACTION->{
                ToastUntil.showToast("clicked it",context?.applicationContext!!)
                Thread{
                    val srcBitmap=BitmapFactory.decodeResource(context.resources,R.drawable.example_appwidget_preview)
                    val appWidgetManager=AppWidgetManager.getInstance(context)
                    for (i in 0 .. 37 ){
                        val degree:Float=(i*10f)%360f
                        val remoteViews=RemoteViews(context.packageName,R.layout.view_remote_widget)
                        remoteViews.setImageViewBitmap(R.id.ivRemoteWidget,
                                rotateBitmap(context,srcBitmap,degree))
                        val clickIntent=Intent()
                        clickIntent.action=CLICK_ACTION
                        val pendingIntent=PendingIntent.getBroadcast(context,0,clickIntent,0)
                        remoteViews.setOnClickPendingIntent(R.id.ivRemoteWidget,pendingIntent)
                        appWidgetManager.updateAppWidget(ComponentName(context,MyAppWidgetProvider::class.java),
                                remoteViews)
                        SystemClock.sleep(30)
                    }
                }.start()

            }
        }
    }

    private fun rotateBitmap(context: Context,bitmap: Bitmap,degree:Float):Bitmap{
        val matrix=Matrix()
        matrix.reset()
        matrix.setRotate(degree)
        return Bitmap.createBitmap(bitmap,0,0,bitmap.width,bitmap.height,matrix,true)

    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Timber.i("onUpdate")
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        Timber.i("onEnabled")
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        Timber.i("onDisabled")
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        const val CLICK_ACTION="com.wk.view.action.CLICK"

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.view_remote_widget)
            val intentClick=Intent()
            intentClick.action=CLICK_ACTION
            val pendingIntent=PendingIntent.getBroadcast(context,0,intentClick,0)
            views.setOnClickPendingIntent(R.id.ivRemoteWidget,pendingIntent)
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

    }
}

