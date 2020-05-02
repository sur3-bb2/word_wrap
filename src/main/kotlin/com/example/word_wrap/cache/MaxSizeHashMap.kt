package com.example.word_wrap.cache

import org.springframework.stereotype.Component
import java.util.*

@Component
class MaxSizeHashMap<K, V> : LinkedHashMap<K, V>() {
    private val maxSize: Int = 100

    override fun removeEldestEntry(eldest: Map.Entry<K, V>): Boolean {
        return size > maxSize
    }
}