package com.example.word_wrap.transformers

import com.example.word_wrap.models.TransformerResponse
import org.springframework.stereotype.Component

@Component("Greedy")
class GreedyTransformer : Transformer {
    override fun transform(input: String, maxWidth: Int): TransformerResponse {
        var count = 0
        var temp = StringBuilder()

        for(i in input.split(" ")) {
            if(count + i.length > maxWidth) {
                count = 0

                val formatted = "${temp.trim()}\n"

                temp.clear()
                temp.append(formatted)
            }

            temp.append("$i ")
            count += (i.length + 1)
        }

        return TransformerResponse(input, temp.trim().toString())
    }
}