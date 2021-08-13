@file:Suppress("UNCHECKED_CAST")

package net.canway.devops.common.starter.utils.proxy

import com.xudh.demo.proxy.exception.OperationException
import net.sf.cglib.proxy.Enhancer
import net.sf.cglib.proxy.MethodInterceptor
import net.sf.cglib.proxy.MethodProxy
import java.lang.reflect.Field
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.Modifier
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

/**
 * Cglib动态代理工厂类，创建执行异步任务的代理对象
 * 1. 被代理类不能是final
 * 2. 需要提供无参构造函数
 * 3. final方法不支持拦截
 */
object CglibProxyFactory {
    private val noOpMethodInterceptor = NoOpMethodInterceptor()
    private val enhancerCache: ConcurrentHashMap<KClass<out Any>, AsyncEnhancer<out Any>> = ConcurrentHashMap()

    /**
     * 生成代理对象
     * kclass
     */
    fun <T> getProxy(klass: KClass<out Any>, clazz: Class<out Any>, body: () -> T): T {
        if (!klass.isOpen || Modifier.isFinal(clazz.modifiers)) {
            // final class不支持继承，不能使用Cglib做动态代理
            throw OperationException("不支持Final类")
        }

        // 创建并缓存异步增强器
        val enhancer = enhancerCache.getOrPut(klass) { createEnhancer(clazz) } as AsyncEnhancer<T>

        // 使用增强器创建动态代理
        return enhancer.createAsyncProxy(body)
    }

    /**
     * 创建增强类
     */
    private fun createEnhancer(clazz: Class<out Any>): AsyncEnhancer<out Any> {
        // 创建异步增强器实例
        val enhancer = AsyncEnhancer<Any>()

        // 设置被代理的类
        enhancer.setSuperclass(clazz)

        // 设置不进行任何操作的空拦截器
        // 使Cglib使用MethodInterceptor进行方法拦截，后面会通过反射替换该拦截器
        enhancer.setCallback(noOpMethodInterceptor)

        return enhancer
    }

    class AsyncEnhancer<T> : Enhancer() {
        // 缓存代理类的Field字段
        private val fieldCache = ConcurrentHashMap<Class<out Any>, Field>()

        fun createAsyncProxy(body: () -> T): T {
            // 创建代理类
            val proxy = super.create()

            // 通过反射重新设置拦截器
            val newInterceptor = AsyncMethodInterceptor(body)

            val field = fieldCache.getOrPut(proxy::class.java) {
                val field = proxy::class.java.getDeclaredField("CGLIB\$CALLBACK_0")
                field.isAccessible = true
                field
            }

            field.set(proxy, newInterceptor)

            return proxy as T
        }
    }

    class AsyncMethodInterceptor<T>(
        body: () -> T
    ) : AsyncProxy<T>(body), MethodInterceptor {
        override fun intercept(obj: Any, method: Method, args: Array<Any>?, proxy: MethodProxy): Any? {
            try {
                // 阻塞获取异步任务的结果
                val realObject = getObject()

                // 反射执行方法
                return if (args.isNullOrEmpty()) {
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

    class NoOpMethodInterceptor : MethodInterceptor {
        override fun intercept(obj: Any, method: Method, args: Array<Any>?, proxy: MethodProxy): Any? {
            return null
        }
    }
}
