package com.luc.base.core.api

import com.ashokvarma.gander.GanderInterceptor
import com.google.gson.GsonBuilder
import com.luc.base.core.extension.getAppContext
import com.luc.base.core.helper.JNIUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitFactory {

    fun retrofitService(forTesting: Boolean? = false): ApiService {
        val isForTesting = forTesting ?: false

        return Retrofit.Builder()
            .baseUrl(
                if (isForTesting)
                    "http://private-0e175-mluckypradana.apiary-mock.com/"
                else
                    JNIUtil.apiEndpoint()
            )
            .client(okHttpClient(isForTesting))
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .setPrettyPrinting()
                        .create()
                )
            )
            .build().create(ApiService::class.java)
    }

    private fun okHttpClient(forTesting: Boolean): OkHttpClient {

        val okHttpBuild = OkHttpClient.Builder()
        okHttpBuild.connectTimeout(30, TimeUnit.SECONDS)
        okHttpBuild.readTimeout(30, TimeUnit.SECONDS)
        okHttpBuild.writeTimeout(30, TimeUnit.SECONDS)
        if (forTesting)
            okHttpBuild.addInterceptor(MockInterceptor())
        else {
            okHttpBuild.addInterceptor(ApiInterceptor())
            okHttpBuild.addInterceptor(
                GanderInterceptor(getAppContext())
                    .showNotification(true)
            )
        }


        return okHttpBuild.build()
    }
}