package com.luc.base.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.luc.base.helper.DateHelper

/**
 * Created by MuhammadLucky on 14/05/2018.
 */
@Entity(tableName = "user_table")
open class User {
    @PrimaryKey
    @SerializedName("mobile")
    var mobile: String = ""
    @SerializedName("first_name")
    var firstName: String = ""
    @SerializedName("last_name")
    var lastName: String = ""
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