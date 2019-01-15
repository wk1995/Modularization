package com.wk.modularization.hardware.surface

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.SurfaceView
import android.view.SurfaceHolder

/**
 * <pre>
 * author : wk
 * e-mail : 122642603@qq.com
 * time   : 2018/10/19
 * GitHub : https://github.com/wk1995
 * CSDN   : http://blog.csdn.net/qq_33882671
 * desc   :
</pre> *
 */
class TestSurfaceView : SurfaceView, SurfaceHolder.Callback, Runnable {

    companion object {
        const val TIME_FLUSH = 30
    }

    constructor(context: Context) : super(context) {
        initView()

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    @Volatile
    private var isDrawing = false
    private var i=0

    private fun initView() {
        holder.addCallback(this)
        isFocusable = true
        isFocusableInTouchMode = true
        keepScreenOn = true
    }


    override fun surfaceCreated(holder: SurfaceHolder) {
        isDrawing = true
        Thread(this).start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        isDrawing = false
    }


    override fun run() {
        while(isDrawing) {
            val startTime = System.currentTimeMillis()
            synchronized(holder) {
                drawCanvas(holder)
            }
            var diffTime = System.currentTimeMillis() - startTime
            while (diffTime <= TIME_FLUSH) {
                diffTime = System.currentTimeMillis() - startTime
                Thread.yield()
            }
        }
    }

    private val paint by lazy {
        val paint= Paint()
        paint.style=Paint.Style.FILL_AND_STROKE
        paint.color= Color.RED
        paint.textAlign=Paint.Align.CENTER
        paint.textSize=32f
        paint
    }
    private fun drawCanvas(holder: SurfaceHolder?) {
        val canvas=holder?.lockCanvas()
        try {

            i++
            //清屏
            canvas?.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
            canvas?.drawText(i.toString(),measuredWidth/2f,measuredHeight/2f,paint)
        } catch (e: Exception) {

        } finally {
            holder?.unlockCanvasAndPost(canvas)
        }
    }

}
