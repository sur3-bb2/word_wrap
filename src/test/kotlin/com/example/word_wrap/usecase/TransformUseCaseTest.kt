package com.example.word_wrap.usecase

import com.example.word_wrap.cache.Cache
import com.example.word_wrap.models.TransformerResponse
import com.example.word_wrap.transformers.Transformer
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.*
import org.mockito.BDDMockito.given
import org.mockito.Mockito.atMost
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
internal class TransformUseCaseShould {
    @MockBean(name = "Greedy")
    private val transformer: Transformer? = null

    @MockBean(name = "LRUCache")
    private val cache: Cache? = null

    @Autowired
    private val classUnderTest: TransformUseCase? = null

    @Test
    fun executeSuccessfullyWhenCacheInNull() {
        val input = "aaa bb cc ddddd"
        val maxWidth = 6
        val expected = TransformerResponse(input, "aaa bb\\ncc\\nddddd")

        given(transformer?.transform(input, maxWidth)).willReturn(expected)
        given(cache?.get<TransformerResponse>(ArgumentMatchers.anyString())).willReturn(null)

        val actual = classUnderTest?.execute(input, maxWidth)

        assertTrue(actual?.output == expected.output)

        Mockito.verify(transformer, atMost(1))?.transform(input, maxWidth)
        Mockito.verify(cache, atMost(1))?.get<TransformerResponse>(ArgumentMatchers.anyString())
        Mockito.verify(cache, atMost(1))?.put("$maxWidth$input", expected)
    }

    @Test
    fun returnFromCache() {
        val input = "aaa bb cc ddddd"
        val maxWidth = 6
        val expected = TransformerResponse(input, "aaa bb\\ncc\\nddddd")

        given(cache?.get<TransformerResponse>(ArgumentMatchers.anyString())).willReturn(expected)

        val actual = classUnderTest?.execute(input, maxWidth)

        assertTrue(actual?.output == expected.output)

        Mockito.verify(transformer, atMost(0))?.transform(input, maxWidth)
        Mockito.verify(cache, atMost(1))?.get<TransformerResponse>(ArgumentMatchers.anyString())
        Mockito.verify(cache, atMost(0))?.put("$maxWidth$input", expected)
    }
}