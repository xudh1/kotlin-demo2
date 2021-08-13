package com.xudh.demo.proxy.exception

open class OperationException(message: String) : RuntimeException(message) {
    companion object {
        const val statusCode = 400
    }
}