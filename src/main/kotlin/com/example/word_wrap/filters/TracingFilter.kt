package com.example.word_wrap.filters

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
@Order(1)
class TracingFilter : Filter {
    private val logger: Logger = LoggerFactory.getLogger(TracingFilter::class.java)

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val req = request as HttpServletRequest
        val start = System.currentTimeMillis()

        try {
            chain!!.doFilter(request, response)
        } finally {
            logger.info("${req.requestURI + req.queryString} took ${System.currentTimeMillis() - start} ms");
        }
    }
}