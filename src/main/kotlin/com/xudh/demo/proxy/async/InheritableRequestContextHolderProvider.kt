package com.xudh.demo.proxy.async

object InheritableRequestContextHolderProvider {
    private var contextHolder: InheritableRequestContextHolder<*>? = null

    fun setContextHolder(contextHolder: InheritableRequestContextHolder<*>) {
        this.contextHolder = contextHolder
    }

    fun getRequestContext(): InheritableRequestContext? {
        return contextHolder?.getRequestContext()
    }

    fun setRequestContext(context: InheritableRequestContext) {
        contextHolder?.uncheckSetRequestContext(context)
    }

    fun removeRequestContext() {
        contextHolder?.removeRequestContext()
    }
}
