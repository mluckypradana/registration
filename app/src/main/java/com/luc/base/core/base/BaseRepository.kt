package com.luc.base.core.base

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.luc.base.App
import com.luc.base.R
import com.luc.base.core.api.ApiService
import com.luc.base.core.extension.getString
import com.luc.base.core.helper.Common
import com.luc.base.core.helper.JwtHelper
import com.luc.base.core.helper.SessionStorage
import com.luc.base.core.helper.toGson
import com.luc.base.event.AutoLogoutEvent
import org.greenrobot.eventbus.EventBus
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response
import java.lang.reflect.Type


open class BaseRepository : KoinComponent {
    internal val api: ApiService by inject()
    internal val sessionStorage: SessionStorage by inject()

    fun <T> Response<BaseResp<T>>.getResponse(): BaseResp<T> {
        if (code() == 401)
            autoLogout()


        return if (App.forTesting) {
            val type = object : TypeToken<BaseResp<T?>?>() {}.type
            return message().toGson(type)
        } else
            body() ?: BaseResp()
    }

    open fun <T> fromJSonList(json: String?, myType: Class<T>?): List<T> {
        val gson = Gson()
        val collectionType = TypeToken.getParameterized(List::class.java, myType).type
        return gson.fromJson(json, collectionType)
    }

    fun <T> getErrorMessage(response: Response<BaseResp<T>>): String {
        return when {
            response.body() != null -> getErrorMessage(response.body())
            response.errorBody() != null ->
                response.errorBody()!!.string().toGson(BaseResp::class.java).message
            else -> getErrorMessage()
        }
    }

    private fun getErrorMessage(): String {
        return getErrorMessage(null)
    }

    private fun getErrorMessage(body: BaseResp<*>?): String {
        val resp = body ?: ErrorResp()
        return if (resp.message.isEmpty()) str(R.string.error_common) else resp.message
    }


    internal fun getErrorMessage(e: Exception): String {
        return if (Common.isProd())
            str(R.string.error_common)
        else
            e.message ?: str(R.string.error_common)
    }


    fun str(resId: Int): String {
        return getString(resId)
    }

    private fun autoLogout() {
        EventBus.getDefault().post(AutoLogoutEvent())
    }

    protected fun encrypt(param: Any?): String {
        return JwtHelper.encoded(param)
    }
}

private fun <T> String.toGson(clazz: Type?): BaseResp<T> {
    return Gson().fromJson(this, clazz)
}
