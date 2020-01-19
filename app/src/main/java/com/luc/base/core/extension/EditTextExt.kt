package com.luc.base.core.extension

import android.widget.EditText
import androidx.databinding.BindingAdapter


@BindingAdapter("errorMessage")
fun EditText.errorMessage(message: String?) {
    error = if (message.isNullOrEmpty()) null else message
}