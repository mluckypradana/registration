package com.luc.base.database.entity

import com.google.gson.annotations.SerializedName
import com.luc.base.helper.DateHelper

/**
 * Created by MuhammadLucky on 14/05/2018.
 */
open class User {
    @SerializedName("id")
    var id: Int? = 0
    @SerializedName("first_name")
    var firstName: String = ""
    @SerializedName("last_name")
    var lastName: String = ""
    @SerializedName("mobile")
    var mobile: String = ""
    @SerializedName("email")
    var email: String = ""
    @SerializedName("gender")
    var gender: String? = null
    @SerializedName("birth_date")
    var birthDate: String? = null

    fun getFormattedBirthDate() = DateHelper.reformat(birthDate)

    companion object {
        const val GENDER_MALE = "m"
        const val GENDER_FEMALE = "f"
    }
}