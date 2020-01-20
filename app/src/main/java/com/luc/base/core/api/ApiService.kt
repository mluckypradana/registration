package com.luc.base.core.api

import com.luc.base.core.base.BaseResp
import com.luc.base.database.entity.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/register")
    suspend fun register(@Body body: User?): Response<BaseResp<User>>
}