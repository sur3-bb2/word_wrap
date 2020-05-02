package com.example.word_wrap.helpers

fun String.getByName(name: String) : String? {
    return this?.run {
        this.split("&").find { it.startsWith("$name=") }?.split("=")?.last()
    }
}