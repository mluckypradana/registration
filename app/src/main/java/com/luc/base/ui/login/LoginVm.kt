package com.luc.base.ui.login

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.luc.base.R
import com.luc.base.api.Resource
import com.luc.base.core.base.BaseViewModel
import com.luc.base.core.extension.getAppContext
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
    var mobileNumberError: ObservableField<String> = ObservableField("")

    init {
        viewModelScope.launch { repo.getUsers() }
    }

    fun login(
        onInvalid: () -> Unit,
        onError: (String?) -> Unit,
        onSuccess: (User?) -> Unit
    ) = viewModelScope.launch {
        val phoneInvalid = mobile.get().orEmpty().isInvalidPhone()
        var isInvalid = true

        mobileNumberError.set("")
        when {
            phoneInvalid != 0 -> mobileNumberError.set(getString(phoneInvalid))
            else -> isInvalid = false
        }
        if (isInvalid) {
            onInvalid()
            return@launch
        }

        when (val res = repo.login(mobile.get())) {
            is Resource.Success -> {
                onSuccess(res.data)
                data.set(res.data)
            }
            is Resource.Error -> res.message?.let { onError(it) }
        }
    }

    fun getGreetingsMessage(user: User?): String =
        getAppContext().resources.getString(R.string.message_greetings_value, user?.firstName)
}