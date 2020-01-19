package com.luc.base.database.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by MuhammadLucky on 14/05/2018.
 */
open class User {
    @SerializedName("id")
    var id: Int? = 0
    @SerializedName("first_name")
    var firstName: String? = null
    @SerializedName("last_name")
    var lastName: String? = null
    @SerializedName("no_id_card")
    var noIdCard: String? = null
    @SerializedName("mobile")
    var mobile: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("name_store")
    var nameStore: String? = null
    @SerializedName("password")
    var password: String? = null
    @SerializedName("gender")
    var gender: String? = null
    @SerializedName("birth_date")
    var birthDate: String? = null
    @SerializedName("address")
    var address: String? = null
    @SerializedName("latitude")
    var latitude = 0.0
    @SerializedName("longitude")
    var longitude = 0.0
    @SerializedName("city_id")
    var cityId = 0
    @SerializedName("zip_code")
    var zipCode: String? = null
    @SerializedName("status")
    var status = 0
    @SerializedName("status_store")
    var statusStore = 0
    @SerializedName("duta_code")
    var dutaCode: String? = null
    @SerializedName("referral_code")
    var referralCode: String? = null
    @SerializedName("token_fcm")
    var tokenFcm: String? = null
    @SerializedName("is_courier")
    var isCourier = 0
    @SerializedName("mitra_id")
    var mitraId = 0
    @SerializedName("image")
    var image: String? = null
    @SerializedName("no_ktp")
    var noKtp: String? = null
    @SerializedName("city")
    var city: String? = null
    @SerializedName("province_id")
    var provinceId: String? = null
    @SerializedName("province")
    var province: String? = null
    @SerializedName("token")
    var token: String? = null
    @SerializedName("created_at")
    var createdAt: String? = null
    @SerializedName("limit")
    var limit = 0L
    @SerializedName("point")
    var point = 0
}