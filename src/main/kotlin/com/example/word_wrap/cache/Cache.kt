package com.example.word_wrap.cache

interface Cache {
    fun <T> put(key: String, value: T)

    fun <T> get(key: String) : T?
}