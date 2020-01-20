package com.luc.base.ui.login

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.luc.base.api.Resource
import com.luc.base.core.base.BaseViewModel
import com.luc.base.core.extension.getString
import com.luc.base.database.entity.User
import com.luc.base.extension.isInvalidPhone
import com.luc.base.repository.UserRepo
import kotlinx.coroutines.launch
import org.koin.core.inject


open class LoginVm(context: Application) : BaseViewModel(context) {
    private val repo: UserRepo by inject()
    var mobile: ObservableField<String> = ObservableField()
    var data: ObservableField<User?> = ObservableField()
    private var mobileNumberError: ObservableField<String> = ObservableField("")

    init {
        viewModelScope.launch { repo.getUsers() }
    }

    fun login(
        onInvalid: () -> Unit,
        onError: (String?) -> Unit,
        onSuccess: (Resource<User>) -> Unit
    ) = viewModelScope.launch {
        val phoneInvalid = mobile.get().orEmpty().isInvalidPhone()
        var isInvalid = true
        when {
            phoneInvalid != 0 -> mobileNumberError.set(getString(phoneInvalid))
            else -> isInvalid = false
        }
        if (isInvalid) {
            onInvalid()
            return@launch
        }

        mobileNumberError.set("")

        when (val res = repo.login(mobile.get())) {
            is Resource.Success -> {
                onSuccess(res)
                data.set(res.data)
            }
            is Resource.Error -> res.message?.let { onError(it) }
        }
    }
}