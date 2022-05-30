package com.example.network

import java.security.MessageDigest
import java.util.*

object ApiKey {

    private const val privateKey = "60fad1c4f98d88c33dc165220d948dec0c6d1e6a"
    const val publicKey = "17509291fec39c09ec90a0d194be6d6f"

    fun getTs(): String = Calendar.getInstance().timeInMillis.toString()

    fun getHash(ts: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        val digested = md5.digest((ts + privateKey + publicKey).toByteArray())
        return digested.joinToString("") {
            String.format("%02x", it)
        }
    }

}