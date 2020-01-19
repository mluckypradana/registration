package com.luc.base.ui.login

import android.app.Application
import android.view.View
import androidx.navigation.findNavController
import com.luc.base.R
import com.luc.base.core.base.BaseViewModel

class LoginVm(context: Application) : BaseViewModel(context) {
    fun onClick(view: View) {
        view.findNavController().navigate(R.id.mainFragment)
    }
}