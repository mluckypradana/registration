package com.luc.base.repository

import com.luc.base.R
import com.luc.base.api.Resource
import com.luc.base.core.base.BaseRepository
import com.luc.base.core.extension.getString
import com.luc.base.database.dao.UserDao
import com.luc.base.database.entity.User


open class UserRepo(private val userDao: UserDao) : BaseRepository() {
    private lateinit var users: List<User>

    suspend fun getUsers() {
        users = userDao.getUsers()
    }

    fun login(mobile: String?): Resource<User> {
        val data = users.find { it.mobile == mobile }
        return if (data != null)
            Resource.Success(data)
        else
            Resource.Error(getString(R.string.error_no_user))
    }

    suspend fun register(data: User): Resource<User> {
        return try {
            val response = api.register(data)
            if (response.isSuccessful) {
                val res = response.getResponse()
                userDao.insert(data)
                Resource.Success(res.data, res.message)
            } else
                Resource.Error(getErrorMessage(response))
        } catch (e: Exception) {
            Resource.Error(getErrorMessage(e))
        }
    }

    fun isMobileNumberExists(mobile: String): Boolean {
        val data = users.find { it.mobile == mobile }
        return data != null
    }
}