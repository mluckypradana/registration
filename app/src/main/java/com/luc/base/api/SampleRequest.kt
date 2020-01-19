package com.maleskuliah.app.model.request

import com.google.gson.annotations.SerializedName
import com.luc.base.core.base.BaseReqs

/**
 * Created by MuhammadLucky on 14/05/2018.
 */
class SampleRequest : BaseReqs() {
    @SerializedName("id")
    var id: String? = null
}
