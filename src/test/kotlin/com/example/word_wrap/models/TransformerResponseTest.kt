package com.example.word_wrap.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class TransformerResponseShould {
    @Test
    fun setInputSuccessfully() {
        val expected = "input"
        val classUnderTest = TransformerResponse(expected, "")

        assertTrue(classUnderTest.input == expected)
    }

    @Test
    fun setOutputSuccessfully() {
        val expected = "output"
        val classUnderTest = TransformerResponse("", expected)

        assertTrue(classUnderTest.output == expected)
    }
}