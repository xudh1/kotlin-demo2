package com.xudh.demo.proxy.async

import java.util.concurrent.ConcurrentHashMap

open class InheritableRequestContext(
    val userId: String,
    val tenantId: String,
    val cache: ConcurrentHashMap<String, Any> = ConcurrentHashMap()
)
