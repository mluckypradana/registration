package com.luc.base.helper

import com.luc.base.core.Constant
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Alvin Rusli on 10/7/2016.
 *
 * A helper class for date/time format.
 */
object DateHelper {

    private fun parseDate(time: String?, inputPattern: String, outputPattern: String): String {
        if (time.isNullOrEmpty()) return ""
        val locale = Locale("in", "EN")
        val inputFormat = SimpleDateFormat(inputPattern, locale)
        val outputFormat = SimpleDateFormat(outputPattern, locale)
        val date: Date?
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.localizedMessage
        }
        return str ?: ""
    }

    fun reformat(dateText: String?): String {
        val containsTime = dateText?.length ?: 0 > Constant.DEFAULT_DATE_INPUT.length
        val inputPattern =
            if (containsTime) Constant.DEFAULT_TIME_INPUT
            else Constant.DEFAULT_DATE_INPUT
        val outputPattern =
            if (containsTime) Constant.DEFAULT_TIME_OUTPUT
            else Constant.DEFAULT_DATE_OUTPUT
        return parseDate(dateText, inputPattern, outputPattern)
    }

    fun format(milis: Long?, format: String = Constant.DEFAULT_DATE_INPUT): String {
        val format1 = SimpleDateFormat(format, Locale("en"))
        return if (milis != null) format1.format(milis) else ""
    }

    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS

}
