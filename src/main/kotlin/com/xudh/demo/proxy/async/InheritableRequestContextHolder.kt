package com.xudh.demo.proxy.async

import javax.annotation.PostConstruct

@Suppress("UNCHECKED_CAST")
abstract class InheritableRequestContextHolder<T : InheritableRequestContext> {
    @PostConstruct
    fun postCreate() {
        InheritableRequestContextHolderProvider.setContextHolder(this)
    }

    private val requestContextLocal = ThreadLocal<T>()

    internal fun uncheckSetRequestContext(requestContext: InheritableRequestContext) = requestContextLocal.set(requestContext as T)

    fun setRequestContext(requestContext: T) = requestContextLocal.set(requestContext)

    fun getRequestContext(): T? = requestContextLocal.get()

    fun unsafeGetRequestContext(): T = requestContextLocal.get()!!

    fun removeRequestContext() = requestContextLocal.remove()

    fun <T> get(key: String): T? {
        val docRequestContext = requestContextLocal.get() ?: return null
        return docRequestContext.cache[key] as T
    }

    fun <T> put(key: String, value: T) {
        val docRequestContext = requestContextLocal.get() ?: return
        docRequestContext.cache[key] = value!! as Any
    }

    fun <T> getOrPut(key: String, body: () -> T): T {
        val threadCache = get<T>(key)
        if (threadCache != null) {
            return threadCache
        }

        return body().apply { put(key, this) }
    }
}
