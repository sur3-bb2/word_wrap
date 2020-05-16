package com.example.word_wrap.transformers

import com.example.word_wrap.models.TransformerResponse
import org.springframework.stereotype.Component

// aaa bb cc ddddd

@Component("Greedy")
class GreedyTransformer : Transformer {
    /*override fun transform(input: String, maxWidth: Int): TransformerResponse {
        var count = 0
        var temp = StringBuilder()

        for(i in input.split(" ")) {
            if(count + i.length > maxWidth) { //count = 3, 3+5 > 6
                count = 0

                val formatted = "${temp.trimEnd()}\n"

                temp.clear()
                temp.append(formatted)
            }

            temp.append("$i ") //ddddd
            count += (i.length + 1) //0+6
        }

        return TransformerResponse(input, temp.trim().toString())
    }*/

   override fun transform(input: String, maxWidth: Int): TransformerResponse {
        if(input.isBlank()
                || (input.length > maxWidth && input.split(" ").size == 1)) return TransformerResponse(input ,input)

        var count = 0
        var temp = StringBuilder()
        var lastSpaceIndex = 0
        var index = 0
       var firstNonSpaceEncountered = false

        while(index < input.length) {
            if(count == maxWidth && (input[index] == ' ' || input[index-1] == ' ')) {
                val formatted = "${temp}\n"

                temp.clear()
                temp.append(formatted)
                count = 0
            } else if(count == maxWidth) {
                val inter = temp.removeRange(lastSpaceIndex, temp.length)

                temp.clear()
                temp.append("$inter\n")

                index = lastSpaceIndex
                count = 0
            }

            temp.append(input[index])
            count++

            if(input[index] == ' ') {
                if(firstNonSpaceEncountered) lastSpaceIndex = index
            } else {
                firstNonSpaceEncountered = true
            }

            index++
        }

        return TransformerResponse(input, temp.toString())
    }
}
/**
 *   1 - temp = aaa , count = 4
 *   2 - temp = aaa bb , count = 7
 *   3 - temp = aaa bb\n, count = 0,
 *   4 - temp = cc , count = 3
 *   5 -  temp = cc\n, count = 0
 *   6 - temp =
        **/