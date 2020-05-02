package com.example.word_wrap.usecase

import com.example.word_wrap.cache.Cache
import com.example.word_wrap.models.TransformerResponse
import com.example.word_wrap.transformers.Transformer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class TransformUseCase  {
    @Autowired
    @Qualifier("Greedy")
    private val transformer: Transformer? = null

    @Autowired
    @Qualifier("LRUCache")
    private val cache: Cache? = null

    fun execute(input: String, maxWidth: Int): TransformerResponse {
        val cacheKey = "$maxWidth$input"
        val cacheItem = cache!!.get<TransformerResponse>(cacheKey)

        return when {
            cacheItem != null -> cacheItem
            else -> {
                val response = transformer!!.transform(input, maxWidth)

                cache.put(cacheKey, response)

                response
            }
        }
    }
}