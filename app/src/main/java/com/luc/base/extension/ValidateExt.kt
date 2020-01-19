package com.luc.base.extension

import android.util.Patterns
import com.luc.base.R
import com.luc.base.core.extension.getAppContext
import com.luc.base.core.extension.getString

fun String.isInvalidPhone(): Int {
    if (isNullOrEmpty()) return R.string.error_empty_phone
    var resId = 0
    if (lessThan(R.integer.min_phone))
        resId = R.string.error_invalid_phone
    if (!matches(Regex(getString(R.string.pattern_phone))))
        resId = R.string.error_invalid_phone
    return resId
}

fun String?.lessThan(minLengthResId: Int): Boolean {
    if (isNullOrEmpty()) return true
    val length = getAppContext().resources.getInteger(minLengthResId)
    return this?.length ?: 0 < length
}

fun String.isInvalidEmail(): Int {
    if (isNullOrEmpty()) return R.string.error_empty_email
    return if (!Patterns.EMAIL_ADDRESS.matcher(this).matches()) R.string.error_invalid_email
    else 0
}