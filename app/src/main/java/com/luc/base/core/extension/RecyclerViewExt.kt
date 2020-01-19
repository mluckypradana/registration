package com.luc.base.core.extension

import android.graphics.drawable.Drawable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("verticalOrientation")
fun RecyclerView.verticalOrientation(isVertical: Boolean) {
    layoutManager = LinearLayoutManager(getAppContext(), if (isVertical) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL, false)
}

@BindingAdapter("dividerResId")
fun RecyclerView.dividerResId(resId: Drawable) {
    val itemDecorator =
        DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    itemDecorator.setDrawable(resId)
    addItemDecoration(itemDecorator)
}