package com.luc.base.helper

import android.app.DatePickerDialog
import android.content.Context
import java.util.*

class ProfileHelper {
    private var date: Calendar? = null

    fun pickDate(context: Context?, minimumAge: Int, onPicked: (Calendar?) -> Unit) {
        val maxDate = Calendar.getInstance()
        maxDate.add(Calendar.YEAR, minimumAge)
        val currentDate = date ?: maxDate
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            date = Calendar.getInstance()
            date?.apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, monthOfYear)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
                onPicked(this)
            }
        }
        val dialog = context?.let {
            DatePickerDialog(
                it, dateListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH),
                currentDate.get(Calendar.DAY_OF_MONTH)
            )
        }
        dialog?.datePicker?.maxDate = maxDate.time.time
        dialog?.show()
    }
}