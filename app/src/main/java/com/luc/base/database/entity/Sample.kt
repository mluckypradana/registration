package com.luc.base.database.entity

import com.google.gson.annotations.SerializedName
import com.luc.base.core.base.BaseModel

/**
 * Created by MuhammadLucky on 14/05/2018.
 */
open class Sample : BaseModel() {
    @SerializedName(value = "title", alternate = ["first_name", "name"])
    var title: String? = null

}