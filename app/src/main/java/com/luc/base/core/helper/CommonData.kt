package com.luc.base.core.helper

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

fun <T> T.json(): String = Gson().toJson(this).replace("\\", "")


fun <T> String.toGson(clazz: Class<T>): T = Gson().fromJson(this, clazz)

fun <T> String.toGson(): T {
    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()
    val collectionType = object : TypeToken<T?>() {}.type
    return gson.fromJson(
        this,
        collectionType
    )
}