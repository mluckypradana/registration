package com.luc.base.core.helper

import android.util.Base64
import android.util.Log
import com.google.gson.Gson
import com.luc.base.R
import com.luc.base.core.extension.getAppContext
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.impl.TextCodec
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset


/**
 * Created by MuhammadLucky on 19/02/2018.
 */

object JwtHelper {
    fun encoded(objectParam: Any?): String {
        //Auth jwt
        val secret = getAppContext().getString(R.string.jwtKey)
        val builder = Jwts.builder()
        builder.setPayload(Gson().toJson(objectParam))
        val encodedSecretKey = TextCodec.BASE64.encode(secret)

        // This generated signing key is   TextCodec.BASE64.decode(secret)
        val encodedText = builder.signWith(SignatureAlgorithm.HS256, encodedSecretKey).compact()
        Common.log(message = encodedText)
        return encodedText
    }

    @Throws(Exception::class)
    fun decoded(encoded: String?, className: Class<*>): Any? {
        try {
            val json = decodedJson(encoded)
            return Gson().fromJson(json, className)
        } catch (e: UnsupportedEncodingException) {
            //Error
        }
        return null
    }

    /**
     * Get decoded json from jwt
     */
    private fun decodedJson(jwtEncoded: String?): String? {
        val split = jwtEncoded!!.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        Log.d("JWT_DECODED", "Header: " + getJson(split[0]))
        return getJson(split[1])
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getJson(strEncoded: String): String {
        val decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE)
        return String(decodedBytes, Charset.forName("UTF-8"))
    }
}