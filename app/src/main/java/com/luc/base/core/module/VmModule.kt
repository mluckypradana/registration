package com.luc.base.core.module

import com.luc.base.core.base.BaseViewModel
import com.luc.base.ui.login.LoginVm
import com.luc.base.ui.register.RegisterVm
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object VmModule {
    val vmModule = module {
        viewModel { BaseViewModel(androidApplication()) }
        viewModel { RegisterVm(androidApplication()) }
        viewModel { LoginVm(androidApplication()) }
    }
}
