package com.example.word_wrap.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.io.IOException
import javax.servlet.http.HttpServletResponse
import javax.validation.ConstraintViolationException

@ControllerAdvice
class CustomGlobalExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(ConstraintViolationException::class)
    @Throws(IOException::class)
    fun handleConstraintViolation(error: ConstraintViolationException, response: HttpServletResponse) {
        response.sendError(HttpStatus.BAD_REQUEST.value(), error.message)
    }
}