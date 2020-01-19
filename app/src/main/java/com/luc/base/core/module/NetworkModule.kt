package com.luc.base.core.module

import com.google.gson.Gson
import com.luc.base.core.api.ApiService
import com.luc.base.core.api.RetrofitFactory
import org.koin.dsl.module

object NetworkModule {
    val networkModule = module {
        single { provideGson() }
        single { RetrofitFactory.retrofitService() }
    }
    val networkMockModule = module {
        single { provideGson() }
        single { provideApiService() }
    }

    private fun provideApiService(): ApiService {
        return RetrofitFactory.retrofitService(true)
    }

    private fun provideGson(): Gson? {
        return Gson().newBuilder().create()
    }
}
