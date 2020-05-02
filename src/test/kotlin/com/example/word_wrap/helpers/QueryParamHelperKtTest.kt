package com.example.word_wrap.helpers

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class QueryParamHelperShoud {
    @Test
    fun notParseEmpty() {
        assertTrue("".getByName("").isNullOrEmpty())
    }

    @Test
    fun parseCorrectly() {
        val query = "a=1&b=2&c=3"

        assertTrue(query.getByName("a") == "1")
        assertTrue(query.getByName("b") == "2")
        assertTrue(query.getByName("c") == "3")
    }
}