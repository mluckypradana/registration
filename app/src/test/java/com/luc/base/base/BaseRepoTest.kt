package com.luc.base.base

import java.io.IOException


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

open class BaseRepoTest : BaseTest() {
    // a method to read text file.
    @Throws(IOException::class)
    fun readFromFile(filename: String?): String {
        val inputStream = javaClass.getResourceAsStream(filename!!)
        val stringBuilder = StringBuilder()
        var i: Int
        val b = ByteArray(4096)
        while (inputStream!!.read(b).also { i = it } != -1)
            stringBuilder.append(String(b, 0, i))
        return stringBuilder.toString()
    }
}
