package com.example.word_wrap.transformers

import com.example.word_wrap.models.TransformerResponse

interface Transformer {
    fun transform(input: String, maxWidth: Int) : TransformerResponse
}