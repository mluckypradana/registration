package com.luc.base.core.api

import com.luc.base.BuildConfig
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            return chain.proceed(chain.request())
                .newBuilder()
                .code(code)
                .protocol(Protocol.HTTP_2)
                .message(responseJson)
                .body(responseJson.toResponseBody("application/json; charset=utf-8".toMediaTypeOrNull()))
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }

    companion object {
        fun setResponse(responseCode: Int, json: String) {
            code = responseCode
            responseJson = json
        }

        var code = 200
        var responseJson = ""
    }
}
//                """
//{
//  "data": {
//    "id": 1,
//    "title": "Jogging in park"
//  },
//  "message": "Success adding a note"
//}
//"""