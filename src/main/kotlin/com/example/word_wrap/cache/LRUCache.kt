package com.example.word_wrap.cache

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("LRUCache")
class LRUCache : Cache {
    @Autowired
    private val store = MaxSizeHashMap<Int, Any>()

    override fun <T> put(key: String, value: T) {
        store[key.hashCode()] = value as Any
    }

    override fun <T> get(key: String): T? {
        return store[key.hashCode()] as T?
    }
}