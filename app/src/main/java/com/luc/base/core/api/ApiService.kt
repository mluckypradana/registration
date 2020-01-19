package com.luc.base.core.api

import com.luc.base.core.base.BaseResp
import com.luc.base.database.entity.Note
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("notes")
    suspend fun createNote(
        @Body request: Note?
    ): Response<BaseResp<Note>>
}