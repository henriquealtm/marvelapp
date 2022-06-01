package com.example.commons

import com.example.commons.extension.handleOpt
import org.junit.Assert.assertEquals
import org.junit.Test

class HandleOptionalUnitTest {

    // Int
    @Test
    fun `WHEN calling Int_handleOpt() with a null value THEN return 0`() {
        val nullInt: Int? = null
        assertEquals(0, nullInt.handleOpt())
    }

    @Test
    fun `WHEN calling Int_handleOpt() with a Int value THEN return the Int value`() {
        assertEquals(123, 123.handleOpt())
    }

    // String
    @Test
    fun `WHEN calling String_handleOpt() with a null value THEN return an empty string`() {
        val nullString: String? = null
        assertEquals("", nullString.handleOpt())
    }

    @Test
    fun `WHEN calling String_handleOpt() with a String value THEN return String value`() {
        assertEquals("hey", "hey".handleOpt())
    }

}