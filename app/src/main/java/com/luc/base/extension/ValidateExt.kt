package com.luc.base.extension

import android.util.Patterns
import com.luc.base.R
import com.luc.base.core.extension.getAppContext

fun String.isInvalidPhone(): Int {
    if (isNullOrEmpty()) return R.string.error_empty_phone
    var resId = 0
    if (lessThan(R.integer.min_phone))
        resId = R.string.error_invalid_phone
    return resId
}

fun String?.lessThan(minLengthResId: Int): Boolean {
    if (isNullOrEmpty()) return true
    val length = getAppContext().resources.getInteger(minLengthResId)
    return this?.length ?: 0 < length
}

fun String.isInvalidPassword(): Int {
    if (isNullOrEmpty()) return R.string.error_empty_password
    return if (length < getAppContext().resources.getInteger(R.integer.min_password))
        R.string.error_invalid_password
    else 0
}

fun String.isInvalidEmail(): Int {
    if (isNullOrEmpty()) return R.string.error_empty_email
    return if (!Patterns.EMAIL_ADDRESS.matcher(this).matches()) R.string.error_invalid_email
    else 0
}