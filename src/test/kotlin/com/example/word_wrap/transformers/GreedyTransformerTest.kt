package com.example.word_wrap.transformers

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class GreedyTransformerShould {
    @Test
    fun transformSuccessfully() {
        val classUnderTest = GreedyTransformer()
        val input = "   aaa bb cc ddddd"
        val maxWidth = 6
        val actual = classUnderTest.transform(input, maxWidth)

        assertNotNull(actual.output)
        assertEquals(actual.output,"   aaa\n bb cc\n ddddd")
    }

    @Test
    fun transformOneWordInput() {
        val classUnderTest = GreedyTransformer()
        val input = "aaaaaaaaabb"
        val maxWidth = 6
        val actual = classUnderTest.transform(input, maxWidth)

        assertNotNull(actual.output)
        assertEquals(actual.output,"aaaaaaaaabb")
    }

    @Test
    fun transformSecondInput() {
        val classUnderTest = GreedyTransformer()
        val input = "  aaa dddddsdsdsdsdsdds"
        val maxWidth = 6

        val actual = classUnderTest.transform(input, maxWidth)

        assertNotNull(actual.output)
        assertTrue(actual.output == "  aaa \ndddddsdsdsdsdsdds")
    }

    @Test
    fun transformTescoDataSuccessfully() {
        val classUnderTest = GreedyTransformer()
        val input = "Design a word wrap micro service which provides functionality to take an input string and wraps it so that none of the lines are longer than the max length. The lines should not break any word in the middle."
        val maxWidth = 23

        val actual = classUnderTest.transform(input, maxWidth)

        assertNotNull(actual.output)
        assertEquals(actual.output, "Design a word wrap \nmicro service which \nprovides functionality \nto take an input string \nand wraps it so that \nnone of the lines are \nlonger than the max \nlength. The lines \nshould not break any \nword in the middle.")
    }
}