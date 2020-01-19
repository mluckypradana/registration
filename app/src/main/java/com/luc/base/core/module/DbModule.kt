package com.luc.base.core.module

import com.luc.base.core.helper.SessionStorage
import com.luc.base.repository.UserRepo
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DbModule {
    val dbModule = module {
        single { SessionStorage(androidContext()) }
        single { UserRepo() }
    }
}
