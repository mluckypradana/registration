package com.luc.base.core.helper

import android.content.Context
import com.luc.base.App
import com.luc.base.core.Constant.ACCESS_TOKEN
import com.luc.base.core.extension.filterEmpty
import com.orhanobut.hawk.Hawk

class SessionStorage(var context: Context) {
    fun <T> put(key: String, value: T) {
        if (!App.forTesting)
            Hawk.put(key, value)
    }

    fun <T> get(key: String): T? {
        return if (!App.forTesting)
            Hawk.get<T>(key)
        else null
    }

    fun saveAccessToken(accessToken: String?) {
        accessToken?.let {
            Hawk.put(ACCESS_TOKEN, accessToken)
        }
    }

    fun getAccessToken(): String {
        return Hawk.get<String>(ACCESS_TOKEN).filterEmpty()
    }
}