package com.example.word_wrap.filters

import com.example.word_wrap.helpers.getByName
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
//import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
@Order(1)
//@WebFilter(urlPatterns = ["/transform/*"])
class AuthFilter : Filter {
    private val logger: Logger = LoggerFactory.getLogger(AuthFilter::class.java)

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val req = request as HttpServletRequest
        val res = response as HttpServletResponse
        val apiKey = req.getHeader(KEY) ?: req.queryString?.getByName(KEY)

        logger.info("Checking API KEY for req : {}", req.requestURI)

        if(apiKey.isNullOrEmpty()) {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API_KEY header" )
            return
        }

        logger.info("Checked API KEY for req : {}", req.requestURI)

        chain!!.doFilter(request, response)
    }

    companion object {
        const val KEY = "API_KEY"
    }
}