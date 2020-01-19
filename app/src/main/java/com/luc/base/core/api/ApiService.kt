package com.luc.base.core.api

import com.luc.base.core.base.BaseResp
import com.luc.base.database.entity.Note
import com.luc.base.database.entity.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("notes")
    suspend fun createNote(
        @Body request: Note?
    ): Response<BaseResp<Note>>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("mobile") mobile: String,
        @Field("password") password: String
    ): Response<BaseResp<User>>

    @POST("register")
    suspend fun register(@Body body: User?): Response<BaseResp<User>>
}