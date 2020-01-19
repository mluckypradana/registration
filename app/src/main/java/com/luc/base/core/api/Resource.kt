package com.luc.base.core.api

// A generic class that contains message and status about loading this message.
sealed class Resource<T>(val data: Any? = null, val message: String? = null) {
    class Success<T>(data: T?, message: String? = null) : Resource<T>(data, message)
    class Error<T>(message: String, data: Any? = null) : Resource<T>(data, message)
}
 