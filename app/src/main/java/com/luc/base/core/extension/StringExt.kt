package com.luc.base.core.extension

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import com.luc.base.R

fun String?.filterEmpty(defaultValue: String = ""): String {
    return this ?: defaultValue
}

/** Use to differentiate text color */
fun String.appendColor(word: String): SpannableString {
    val spannableStr = SpannableString(this)
    spannableStr.setSpan(
        ForegroundColorSpan(ContextCompat.getColor(getAppContext(), R.color.colorAccent)),
        this.indexOf(word),
        this.indexOf(word) + word.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return spannableStr
}