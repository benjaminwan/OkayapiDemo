package com.benjaminwan.okayapidemo.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide


/*
    Glide工具类
 */
object GlideUtils {
    fun loadImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).centerCrop().into(imageView)
    }

    fun loadImageFitCenter(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).fitCenter().into(imageView)
    }

    fun loadByteArray(context: Context, data: ByteArray, imageView: ImageView) {
        Glide.with(context).load(data).fitCenter().into(imageView)
    }

}
