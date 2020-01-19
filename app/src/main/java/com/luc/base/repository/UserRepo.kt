package com.luc.base.repository

import com.luc.base.api.Resource
import com.luc.base.core.base.BaseRepository
import com.luc.base.core.base.BaseResp
import com.luc.base.database.entity.User
import java.net.UnknownHostException


open class UserRepo : BaseRepository() {
    suspend fun login(phone: String, password: String): Resource<User> {
        return try {
            val response = api.login(phone, password)
            if (response.isSuccessful) {
                val res: BaseResp<User> = response.getResponse()
                sessionStorage.saveAccessToken(res.data?.token)
                Resource.Success(res.data, res.message)
            } else
                Resource.Error(getErrorMessage(response))
        } catch (e: UnknownHostException) {
            Resource.Error(getErrorMessage(e))
        }
    }

    suspend fun register(data: User): Resource<User> {
        return try {
            val response = api.register(data)
            if (response.isSuccessful) {
                val res = response.getResponse()
                data.password = null
                Resource.Success(res.data, res.message)
            } else
                Resource.Error(getErrorMessage(response))
        } catch (e: UnknownHostException) {
            Resource.Error(getErrorMessage(e))
        }
    }
}