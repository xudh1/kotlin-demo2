@file:Suppress("UNCHECKED_CAST")

package net.canway.devops.common.starter.utils.proxy

import com.xudh.demo.proxy.exception.OperationException
import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * JDK动态代理工厂类，创建执行异步任务的代理对象
 * 1. 被代理类传入的泛型只能是接口
 */
object JdkDynamicProxyFactory {

    fun <T> getProxy(clazz: Class<out Any>, body: () -> T): T {
        if (!clazz.isInterface) {
            throw OperationException("动态代理只支持Interface")
        }
        // 使用JDK动态代理创建代理对象
        return Proxy.newProxyInstance(clazz.classLoader, arrayOf(clazz), AsyncJdkDynamicInvocationHandler(body)) as T
    }

    private class AsyncJdkDynamicInvocationHandler<T>(
        body: () -> T
    ) : AsyncProxy<T>(body), InvocationHandler {
        override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any? {
            try {
                // 阻塞获取异步任务的结果
                val realObject = getObject()

                // 反射执行方法
                return if (args == null) {
                    method.invoke(realObject)
                } else {
                    method.invoke(realObject, *args)
                }
            } catch (e: InvocationTargetException) {
                // 反射方法执行失败时的异常，来自method.invoke
                throw e.targetException
            }
        }
    }
}
