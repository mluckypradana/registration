package com.luc.base.core.base

import com.google.gson.annotations.SerializedName
import com.luc.base.BuildConfig

/**
 * Created by MuhammadLucky on 14/05/2018.
 */
open class BaseReqs {
    @SerializedName("version")
    var version: String? = BuildConfig.VERSION_NAME
}
