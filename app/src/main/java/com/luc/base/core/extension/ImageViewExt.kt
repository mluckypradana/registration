package com.luc.base.core.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luc.base.R


@BindingAdapter("circleLoadImage")
fun ImageView.circleLoadImage(imageSource: String) {
    urCircleImage(imageSource, R.drawable.bg_placeholder)
}

fun ImageView.urCircleImage(imageSource: String, placeholder: Int) {
    Glide.with(context)
        .load(imageSource.filterEmpty())
        .apply(RequestOptions.circleCropTransform().placeholder(placeholder))
        .into(this)
}


@BindingAdapter("loadResource")
fun ImageView.loadResource(imageId: Int) {
    loadResourceImage(imageId, R.drawable.bg_placeholder)
}


@BindingAdapter("srcUrl")
fun ImageView.srcUrl(url: String) {
    loadResourceImage(url, R.drawable.bg_placeholder)
}

fun ImageView.loadResourceImage(imageId: Int, placeholder: Int) {
    Glide.with(context)
        .load(imageId)
        .apply(RequestOptions().placeholder(placeholder))
        .into(this)
}

fun ImageView.loadResourceImage(url: String, placeholder: Int) {
    Glide.with(context)
        .load(url)
        .apply(RequestOptions().placeholder(placeholder))
        .into(this)
}