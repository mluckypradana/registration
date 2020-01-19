package com.luc.base.core.helper

object JNIUtil {

    init {
        System.loadLibrary("native-lib")
    }

    external fun apiEndpoint(): String
}