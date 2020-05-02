package com.example.word_wrap.models

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class TransformerRequest(@Min(1)
                              val maxWidth: Int,
                              @NotNull(message = "input is mandatory")
                              @NotBlank(message = "input is mandatory")
                              val input: String)