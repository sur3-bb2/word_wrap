package com.example.word_wrap

import com.example.word_wrap.models.TransformerResponse
import com.example.word_wrap.usecase.TransformUseCase
import org.hamcrest.CoreMatchers.containsStringIgnoringCase
import org.hamcrest.core.Is
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.atMost
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.core.io.buffer.DataBufferUtils.matcher
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(Controller::class)
internal class ControllerShould {
    @Autowired
    private val mockMvc: MockMvc? = null

    @MockBean
    private val transformUseCase: TransformUseCase? = null

    @Test
    @Throws(Exception::class)
    fun return401OnInvalidApiKey() {
        this.mockMvc?.
                perform(get("/transform?maxWidth=6&input=aaa%20bb%20cc%20ddddd"))?.
                andDo(print())?.
                andExpect(status().isUnauthorized)
    }

    @Test
    @Throws(Exception::class)
    fun return400OnInvalidIndex() {
        this.mockMvc?.
                perform(get("/transform?API_KEY=123&maxWidth=-1&input=aaa%20bb%20cc%20ddddd"))?.
                andDo(print())?.
                andExpect(status().isBadRequest)?.
                andExpect {
                    Assertions.assertEquals(it.response.errorMessage, "transform.maxWidth: must be greater than or equal to 1")
                }
    }

    @Test
    @Throws(Exception::class)
    fun return400OnInvalidInput() {
        this.mockMvc?.
                perform(get("/transform?API_KEY=123&maxWidth=1&input="))?.
                andDo(print())?.
                andExpect(status().isBadRequest)?.
                andExpect {
                    Assertions.assertEquals(it.response.errorMessage, "transform.input: must not be blank")
                }
    }

    @Test
    @Throws(Exception::class)
    fun transformWhenCacheIsEmpty() {
        val input = "aaa%20bb%20cc%20ddddd"
        val maxWidth = 6
        val actual = TransformerResponse(input, "aaa bb\\ncc\\nddddd")
        val actualString = "{\"input\":\"aaa%20bb%20cc%20ddddd\",\"output\":\"aaa bb\\\\ncc\\\\nddddd\"}"

        given(transformUseCase?.execute(input, maxWidth)).willReturn(actual)

        this.mockMvc?.
                perform(get("/transform?maxWidth=6&input=aaa%20bb%20cc%20ddddd&API_KEY=123"))?.
                andDo(print())?.
                andExpect(status().isOk)?.
                andExpect(content().string(containsStringIgnoringCase(actualString)))

        Mockito.verify(transformUseCase, atMost(1))?.execute(input, maxWidth)
    }
}