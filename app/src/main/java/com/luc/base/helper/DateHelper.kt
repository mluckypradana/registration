package com.luc.base.helper

import com.luc.base.core.Constant
import com.luc.base.core.helper.Common
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Alvin Rusli on 10/7/2016.
 *
 * A helper class for date/time format.
 */
object DateHelper {

    /**
     * Convert time and date to millis.
     * @param dateTime The string to parse
     * @return time in millis
     */
    fun convertToMillis(dateTime: String, dateFormat: String): Long {
        val formatter = SimpleDateFormat(dateFormat, Locale.US)
        return formatter.parse(dateTime).time
    }

    /**
     * Obtain a [Calendar] object from a String.
     * @return the [Calendar] object
     */
    fun getCalendarFromMillis(millis: Long): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return calendar
    }

    fun getCurrentDate(): String {
        return format(milis = Calendar.getInstance().time.time)
    }

    /**
     * Obtain a [Calendar] object from a String.
     * @return the [Calendar] object
     */
    fun getCalendarFromDate(dateTime: String?, dateFormat: String): Calendar? {
        return try {
            val formatter = SimpleDateFormat(dateFormat, Locale.US)
            val date = formatter.parse(dateTime)
            val calendar = Calendar.getInstance()
            calendar.time = date
            calendar
        } catch (e: ParseException) {
            Common.printStackTrace(e)
            null
        }
    }


    private fun parseDate(time: String?, inputPattern: String, outputPattern: String): String {
        if (time.isNullOrEmpty()) return ""
        val localeId = Locale("in", "ID")
        val inputFormat = SimpleDateFormat(inputPattern, localeId)
        val outputFormat = SimpleDateFormat(outputPattern, localeId)
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

    fun reformat(dateText: String?, inputPattern: String, outputPattern: String): String? {
        return parseDate(dateText, inputPattern, outputPattern)
    }

    fun format(milis: Long?, format: String = Constant.DEFAULT_DATE_INPUT): String {
        val format1 = SimpleDateFormat(format, Locale("id"))
        return if (milis != null) format1.format(milis) else ""
    }

    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS

    fun getRelativeTimeFormat(date: String?): String {
        val format = SimpleDateFormat(Constant.DEFAULT_TIME_INPUT, Locale.ENGLISH)
        var time = try {
            format.parse(date.orEmpty())?.time ?: 0L
        } catch (e: ParseException) {
            0L
        }
        if (time < 1000000000000L)
            time *= 1000
        val now = System.currentTimeMillis()
        if (time > now || time <= 0) {
            val cal = Calendar.getInstance()
            cal.time.time = time
            return reformat(date)
        }
        val diff = now - time
        return when {
            diff < MINUTE_MILLIS -> "Sekarang"
            diff < 2 * MINUTE_MILLIS -> "semenit lalu"
            diff < 50 * MINUTE_MILLIS -> (diff / MINUTE_MILLIS).toString() + " menit yang lalu"
            diff < 90 * MINUTE_MILLIS -> "an hour ago"
            diff < 24 * HOUR_MILLIS -> (diff / HOUR_MILLIS).toString() + " jam yang lalu"
            diff < 48 * HOUR_MILLIS -> "kemarin"
            else -> (diff / DAY_MILLIS).toString() + " hari yang lalu"
        }
    }
}