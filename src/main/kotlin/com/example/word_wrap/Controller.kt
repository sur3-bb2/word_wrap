package com.example.word_wrap

import com.example.word_wrap.models.TransformerRequest
import com.example.word_wrap.usecase.TransformUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

@RestController
@Validated
class Controller {
    @Autowired
    val transformUseCase: TransformUseCase? = null

    @GetMapping("/transform")
    fun transform(@RequestParam(value = "maxWidth")
                  @Min(1)
                  maxWidth: Int,
                  @RequestParam(value = "input")
                  @NotBlank
                  input: String)
        = transformUseCase?.execute(input, maxWidth)

    /*@PostMapping("/post")
    fun postTransform(@RequestBody request: TransformerRequest)
            = transformUseCase?.execute(request.input, request.maxWidth)*/
}