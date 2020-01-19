package com.luc.base.core.extension

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import org.koin.core.context.GlobalContext


fun getAppContext() = GlobalContext.get().koin.get<Application>()
fun getString(id: Int) = getAppContext().getString(id)

fun <T> Context.getMockResponse(path: String, responseObj: Class<T>): T {
    val inputStream = assets.open(path)
    val strMockResponse = inputStream.bufferedReader().use { it.readText() }
    val mockResponse = Gson().fromJson(strMockResponse, responseObj)
    inputStream.close()
    return mockResponse
}

fun Context.getMockResponse(path: String): String {
    val inputStream = assets.open(path)
    return inputStream.bufferedReader().use { it.readText() }
}