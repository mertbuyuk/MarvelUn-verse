package com.mb.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object Constants {

    const val BASE_URL = "http://gateway.marvel.com/"

    val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
    const val API_KEY = "key"
    const val PRIVATE_KEY = "private key"
    const val limit = "30"
    fun hash(): String {
        val input = "$timeStamp$PRIVATE_KEY$API_KEY"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
    }
}