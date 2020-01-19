package com.luc.base.core.api

import com.ashokvarma.gander.GanderInterceptor
import com.luc.base.core.extension.getAppContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitFactory {
    fun retrofitService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://private-0e175-mluckypradana.apiary-mock.com")
            .client(okHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    private fun okHttpClient(): OkHttpClient {

        val okHttpBuild = OkHttpClient.Builder()
        okHttpBuild.apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            addInterceptor(ApiInterceptor())
            addInterceptor(
                GanderInterceptor(getAppContext())
                    .showNotification(true)
            )
        }
        return okHttpBuild.build()
    }
}