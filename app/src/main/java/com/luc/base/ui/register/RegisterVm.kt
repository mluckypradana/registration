package com.luc.base.ui.register

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.luc.base.R
import com.luc.base.api.Resource
import com.luc.base.core.Constant
import com.luc.base.core.base.BaseViewModel
import com.luc.base.core.extension.getAppContext
import com.luc.base.core.extension.getString
import com.luc.base.core.helper.DateHelper
import com.luc.base.database.entity.User
import com.luc.base.extension.isInvalidEmail
import com.luc.base.extension.isInvalidPhone
import com.luc.base.repository.UserRepo
import kotlinx.coroutines.launch
import org.koin.core.inject
import java.util.*


open class RegisterVm(context: Application) : BaseViewModel(context) {
    private val repo: UserRepo by inject()
    var data: ObservableField<User> = ObservableField()
    var mobileNumberError: ObservableField<String> = ObservableField("")
    var firstNameError: ObservableField<String> = ObservableField("")
    var lastNameError: ObservableField<String> = ObservableField("")
    var emailError: ObservableField<String> = ObservableField("")

    init {
        data = ObservableField(User())
        viewModelScope.launch { repo.getUsers() }
    }

    fun register(
        onInvalid: () -> Unit,
        onError: (String?) -> Unit,
        onSuccess: (Resource<User>) -> Unit
    ) = viewModelScope.launch {
        val data = data.get() ?: User()
        val phoneInvalid = data.mobile.isInvalidPhone()
        val emailInvalid = data.email.isInvalidEmail()
        val phoneExists = repo.isMobileNumberExists(data.mobile)
        data.apply {
            mobileNumberError.set("")
            firstNameError.set("")
            lastNameError.set("")
            emailError.set("")

            var isInvalid = true
            when {
                phoneInvalid != 0 -> mobileNumberError.set(getString(phoneInvalid))
                phoneExists -> mobileNumberError.set(getString(R.string.error_unique_phone))
                firstName.isEmpty() -> firstNameError.set(getString(R.string.error_empty_first_name))
                lastName.isEmpty() -> lastNameError.set(getString(R.string.error_empty_last_name))
                emailInvalid != 0 -> emailError.set(getString(emailInvalid))
                else -> isInvalid = false
            }
            if (isInvalid) {
                onInvalid()
                return@launch
            }

            when (val res = repo.register(data)) {
                is Resource.Success -> onSuccess(res)
                is Resource.Error -> res.message?.let { onError(it) }
            }
        }
    }

    fun setDob(date: Calendar?) {
        data.get()?.birthDate = DateHelper.format(date?.time?.time, Constant.DEFAULT_DATE_INPUT)
    }

    fun setGender(gender: String) {
        data.get()?.gender = gender
    }

    fun getMinAgeForDatePicker(): Int = getAppContext().resources.getInteger(R.integer.min_age) * -1
}