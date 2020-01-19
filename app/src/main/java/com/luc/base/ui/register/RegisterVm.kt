package com.luc.base.ui.register

import android.app.Application
import androidx.databinding.InverseMethod
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.luc.base.R
import com.luc.base.api.Resource
import com.luc.base.core.Constant
import com.luc.base.core.base.BaseViewModel
import com.luc.base.core.extension.getAppContext
import com.luc.base.core.extension.getString
import com.luc.base.database.entity.User
import com.luc.base.database.entity.Value
import com.luc.base.extension.isInvalidEmail
import com.luc.base.extension.isInvalidPhone
import com.luc.base.helper.DateHelper
import com.luc.base.repository.UserRepo
import kotlinx.coroutines.launch
import org.koin.core.inject
import java.util.*


open class RegisterVm(context: Application) : BaseViewModel(context) {
    var data: ObservableField<User> = ObservableField()
    var mobileNumberError: ObservableField<String> = ObservableField("")
    var firstNameError: ObservableField<String> = ObservableField("")
    var lastNameError: ObservableField<String> = ObservableField("")
    var emailError: ObservableField<String> = ObservableField("")
    private val repo: UserRepo by inject()
    private val genderNames = getAppContext().resources.getStringArray(R.array.gender_name)

    init {
        data = ObservableField(User())
    }

    fun register(
        onError: (String) -> Unit,
        onSuccess: (Resource<User>) -> Unit
    ) = viewModelScope.launch {
        val data = data.get() ?: User()
        val phoneInvalid = data.mobile.isInvalidPhone()
        val emailInvalid = data.email.isInvalidEmail()
        data.apply {
            when {
                phoneInvalid != 0 -> mobileNumberError.set(getString(phoneInvalid))
                firstName.isEmpty() -> firstNameError.set(getString(R.string.error_empty_first_name))
                lastName.isEmpty() -> lastNameError.set(getString(R.string.error_empty_last_name))
                emailInvalid != 0 -> emailError.set(getString(emailInvalid))
                else -> {
                    mobileNumberError.set("")
                    firstNameError.set("")
                    lastNameError.set("")
                    emailError.set("")
                }
            }
        }

        when (val res = repo.register(data)) {
            is Resource.Success<*> -> onSuccess(res)
            is Resource.Error -> res.message?.let { onError(it) }
        }
    }

    fun setDob(date: Calendar?) {
        data.get()?.birthDate = DateHelper.format(date?.time?.time, Constant.DEFAULT_DATE_INPUT)
    }

    /** From 1 to Laki-laki */
    @InverseMethod("getGender")
    fun setGender(gender: String): String { //...
        data.get()?.gender = gender
        return gender
    }

    /** From Laki-laki to 1 */
    fun getGender(gender: String?): String {
        val genderValue = gender?.toInt()
        val index = (genderValue ?: 1) - 1
        return if (genderValue == null) "" else genderNames[index]
    }

    fun getGenderValues(): MutableList<Value> {
        val list = arrayListOf<Value>()
        val value = getAppContext().resources.getStringArray(R.array.gender_value)
        genderNames.forEachIndexed { index, s -> list.add(Value(value[index], s)) }
        return list
    }

    fun getMinAgeForDatePicker(): Int = getAppContext().resources.getInteger(R.integer.min_age) * -1
}