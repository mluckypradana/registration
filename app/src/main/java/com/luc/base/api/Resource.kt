package com.luc.base.api

// A generic class that contains message and status about loading this message.
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T? = null, message: String? = null) : Resource<T>(data, message)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(message: String? = null, data: T? = null) : Resource<T>(data, message)
}
 