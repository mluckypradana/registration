package com.luc.base.core.base

import com.google.gson.annotations.SerializedName

/**
 * Response with generic message
 * Created by MuhammadLucky on 14/05/2018.
 */
open class BaseResp<T> {
    var code: Int = 0
    @SerializedName("message")
    var message: String = ""
    @SerializedName("data")
    var data: T? = null
}