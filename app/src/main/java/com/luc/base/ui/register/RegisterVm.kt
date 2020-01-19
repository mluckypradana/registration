package com.luc.base.ui.register

import android.app.Application
import androidx.databinding.InverseMethod
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.luc.base.R
import com.luc.base.api.Resource
import com.luc.base.core.Constant
import com.luc.base.core.base.BaseViewModel
import com.luc.base.core.extension.getAppContext
import com.luc.base.core.extension.getString
import com.luc.base.database.entity.User
import com.luc.base.database.entity.Value
import com.luc.base.extension.isInvalidPassword
import com.luc.base.extension.isInvalidPhone
import com.luc.base.helper.DateHelper
import com.luc.base.repository.UserRepo
import kotlinx.coroutines.launch
import org.koin.core.inject
import java.util.*


open class RegisterVm(context: Application) : BaseViewModel(context) {
    var data: ObservableField<User> = ObservableField()
    var confirmPassword = MutableLiveData("")
    private val repo: UserRepo by inject()
    private val genderNames = getAppContext().resources.getStringArray(R.array.gender_name)

    init {
        data = ObservableField(User())
    }

    fun postProfile(
        isEdit: Boolean? = false,
        onValidate: (String) -> Unit,
        onError: (Resource<User>) -> Unit,
        onSuccess: (Resource<User>) -> Unit
    ) = viewModelScope.launch {
        val editProfile = isEdit ?: false
        val data = data.get() ?: User()
        val phoneInvalid = data.mobile.orEmpty().isInvalidPhone()
        val passwordInvalid = data.password.orEmpty().isInvalidPassword()
        data.apply {
            val errorResId = when {

                else -> 0
            }
            if (errorResId != 0) {
                onValidate(getString(errorResId))
                return@launch
            }
        }

        when (val res = repo.register(data)) {
            is Resource.Success<*> -> onSuccess(res)
            is Resource.Error -> onError(res)
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

    fun setProvince(value: Value) = data.get()?.apply {
        province = value.title
        provinceId = value.value
    }
}