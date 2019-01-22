package com.wk.picture.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.PixelFormat
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.NinePatchDrawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.wk.common.base.BaseActivity
import com.wk.common.constant.RouterPath
import com.wk.picture.R
import kotlinx.android.synthetic.main.pic_activity_memory_comparison.*

/**
 * 内存比较
 * */
@Route(path = RouterPath.MemoryComparisonActivity)
class MemoryComparisonActivity : BaseActivity(), View.OnClickListener {
    private var pic= R.drawable.timg
    override fun getLayoutResource() = R.layout.pic_activity_memory_comparison

    override fun initView() {}

    override fun initListener() {
        btnDirect.setOnClickListener(this)
        btnDecode.setOnClickListener(this)
        btnGlider.setOnClickListener(this)
        btnFactory.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when (p0) {
            btnDirect->{
                ivShowPicLeft.setImageDrawable(ContextCompat.getDrawable(this,getPic()))
                ivShowPicRight.setImageDrawable(ContextCompat.getDrawable(this,getPic()))
            }
            btnGlider->{
                Glide.with(this).load(getPic()).into(ivShowPicLeft)
                Glide.with(this).load(getPic()).into(ivShowPicRight)
            }
            btnDecode->{
                val drawable=ContextCompat.getDrawable(this,getPic())
                ivShowPicLeft.setImageBitmap(getBitmapDrawable(drawable).bitmap)
                ivShowPicRight.setImageBitmap(getBitmapDrawable(drawable).bitmap)
            }
            btnFactory->{
                val bitmap=BitmapFactory.decodeResource(resources,getPic())
                ivShowPicLeft.setImageBitmap(bitmap)
                ivShowPicRight.setImageBitmap(bitmap)
            }

        }
        calculateMemory()
    }

    private fun getPic():Int{
        if(rbPic1.isChecked)
            pic= R.drawable.timg
        else if(rbPic2.isChecked){
            pic= R.drawable.compass_e
        }
        else if(rbPic3.isChecked)
            pic= R.drawable.ic_launcher_background
        return pic
    }

    private fun calculateMemory() {
        val bitmapLeft = getBitmapFromDrawable(ivShowPicLeft.drawable)
        val bitmapRight = getBitmapFromDrawable(ivShowPicRight.drawable)

        tvMemoryValue.text="left ${unitConversionSize(getBitmapMemory(bitmapLeft).toLong())}" +
                " right ${unitConversionSize(getBitmapMemory(bitmapRight).toLong())}"

    }

    //文件大小单位转换
    private fun unitConversionSize(size: Long): String {
        var mSize=size.toDouble()
        if (mSize < 1024)
            return "$mSize B"
        mSize/=1024
        if(mSize<1024)
            return "$mSize K"
        mSize/=1024
        if(mSize<1024)
            return "$mSize M"
        mSize/=1024

        return "$mSize G"
    }

    private fun getBitmapMemory(bitmap: Bitmap?): Int {
        val bitmapConfig = bitmap?.config
        //RGB565是2byte,ARGB4444也是2byte,而ALPHA_8则为1byte.ARGB_8888 4byte
        var unitPb = 0
        when (bitmapConfig) {
            Bitmap.Config.ALPHA_8 -> {
                unitPb = 1
            }
            Bitmap.Config.RGB_565 -> {
                unitPb = 2
            }
            Bitmap.Config.ARGB_4444 -> {
                unitPb = 2
            }
            Bitmap.Config.ARGB_8888 -> {
                unitPb = 4
            }
            else -> {
                if (Build.VERSION.SDK_INT > 26) {
                    if (bitmapConfig == Bitmap.Config.RGBA_F16)
                        unitPb = 8
                }
            }

        }
        return bitmap?.width ?: 0* (bitmap?.height ?: 0) *unitPb
    }

    private fun getBitmapFromDrawable(drawable: Drawable?): Bitmap? {
        var bitmap: Bitmap? = null
        if (drawable is BitmapDrawable)
            bitmap = drawable.bitmap
        else if (drawable is NinePatchDrawable) {
            bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            if (drawable.getOpacity() != PixelFormat.OPAQUE)
                                Bitmap.Config.ARGB_8888
                            else
                                Bitmap.Config.RGB_565)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight())
            drawable.draw(canvas)
        }
        return bitmap

    }

    private fun getBitmapDrawable(drawable: Drawable?):BitmapDrawable{
        if(drawable is BitmapDrawable)
            return drawable
        else{
            val bitmap = Bitmap.createBitmap(drawable?.intrinsicWidth?:0,
                    drawable?.intrinsicHeight?:0, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable?.setBounds(0, 0, canvas.width, canvas.height)
            drawable?.draw(canvas)
            return BitmapDrawable(resources, bitmap)
        }
    }



}




