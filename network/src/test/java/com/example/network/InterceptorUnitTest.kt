package com.example.network

import io.mockk.every
import io.mockk.mockk
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class InterceptorUnitTest {

    @Test
    fun `GIVEN the initial state WHEN calling getNewUrlWithQueryParameters() THEN return a valid HttpUrl`() {
        val httpUrl = "https://gateway.marvel.com:443/".toHttpUrl()
        val newUrl = getNewUrlWithQueryParameters(httpUrl, "1654041635344")

        assertEquals("ts", newUrl.queryParameterName(0))
        assertEquals("1654041635344", newUrl.queryParameterValue(0))

        assertEquals("apikey", newUrl.queryParameterName(1))
        assertEquals("17509291fec39c09ec90a0d194be6d6f", newUrl.queryParameterValue(1))

        assertEquals("hash", newUrl.queryParameterName(2))
        assertEquals("ec8b00c8ed4039f02a6263b9dfe9ef80", newUrl.queryParameterValue(2))
    }

}