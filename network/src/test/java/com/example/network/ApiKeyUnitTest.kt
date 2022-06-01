package com.example.network

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ApiKeyUnitTest {

    @Test
    fun `GIVEN the initial state WHEN calling getHash() THEN return a valid String`() {
        val apiKeyObject = mockk<ApiKey>()
        every { apiKeyObject.getTs() } returns "1654041635344"
        assertEquals("ec8b00c8ed4039f02a6263b9dfe9ef80", ApiKey.getHash(apiKeyObject.getTs()))
    }

}