package com.example.word_wrap.cache

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MaxSizeHashMapShould {
    @Test
    fun setAndRetrieveValueSuccessfully() {
        val classUnderTest = MaxSizeHashMap<String, String>()

        classUnderTest["1"] = "one"
        classUnderTest["2"] = "two"
        classUnderTest["3"] = "three"

        assertTrue(classUnderTest.size == 3)
        assertTrue(classUnderTest["1"] == "one")
        assertTrue(classUnderTest["2"] == "two")
        assertTrue(classUnderTest["3"] == "three")
    }
}