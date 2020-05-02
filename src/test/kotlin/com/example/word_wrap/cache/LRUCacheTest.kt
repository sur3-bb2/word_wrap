package com.example.word_wrap.cache

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LRUCacheTest {
    @Test
    fun put() {
        val classUnderTest = LRUCache()

        classUnderTest.put("a", 1)
        classUnderTest.put("a", 22)
        assertTrue(classUnderTest.get<Int>("a") == 22)
    }

    @Test
    fun get() {
        val classUnderTest = LRUCache()

        classUnderTest.put("a", 1)
        classUnderTest.put("b", 2)

        assertTrue(classUnderTest.get<Int>("a") == 1)
        assertTrue(classUnderTest.get<Int>("b") == 2)
    }
}