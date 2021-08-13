@file:Suppress("NOTHING_TO_INLINE")

package com.xudh.demo.proxy.async

import net.canway.devops.common.starter.utils.proxy.CglibProxyFactory
import net.canway.devops.common.starter.utils.proxy.JdkDynamicProxyFactory
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.TimeUnit

val DOC_EXECUTOR: ExecutorService = AsyncThreadPoolExecutor(
    "doc",
    30,
    100,
    60L,
    TimeUnit.SECONDS,
    SynchronousQueue()
)

//异步代理
inline fun <reified T : Any> asyncProxy(noinline body: () -> T): T {
    val clazz = T::class.java
    return if (clazz.isInterface) {
        JdkDynamicProxyFactory.getProxy(clazz, body)
    } else {
        CglibProxyFactory.getProxy(T::class, clazz, body)
    }
}

inline fun <T> async(noinline callableBody: () -> T): Future<T> = DOC_EXECUTOR.submit(callableBody)

inline fun <A, B> sync2(
    noinline callableBody1: () -> A,
    noinline callableBody2: () -> B
): Pair<A, B> {
    return Pair(
        DOC_EXECUTOR.submit(callableBody1).get(),
        DOC_EXECUTOR.submit(callableBody2).get()
    )
}

inline fun <A, B> async2(
    noinline callableBody1: () -> A,
    noinline callableBody2: () -> B
): Future<Pair<A, B>> {
    return DOC_EXECUTOR.submit<Pair<A, B>> {
        sync2(callableBody1, callableBody2)
    }
}

inline fun <A, B, C> sync3(
    noinline callableBody1: () -> A,
    noinline callableBody2: () -> B,
    noinline callableBody3: () -> C
): Triple<A, B, C> {
    return try {
        Triple(
            DOC_EXECUTOR.submit(callableBody1).get(),
            DOC_EXECUTOR.submit(callableBody2).get(),
            DOC_EXECUTOR.submit(callableBody3).get()
        )
    } catch (e: Exception) {
        throw e.cause!!
    }
}

inline fun <A, B, C> async3(
    noinline callableBody1: () -> A,
    noinline callableBody2: () -> B,
    noinline callableBody3: () -> C
): Future<Triple<A, B, C>> {
    return DOC_EXECUTOR.submit<Triple<A, B, C>> {
        sync3(callableBody1, callableBody2, callableBody3)
    }
}

inline fun <A, B, C> async3Future(
    noinline callableBody1: () -> A,
    noinline callableBody2: () -> B,
    noinline callableBody3: () -> C
): Triple<Future<A>, Future<B>, Future<C>> {
    return Triple(
        DOC_EXECUTOR.submit(callableBody1),
        DOC_EXECUTOR.submit(callableBody2),
        DOC_EXECUTOR.submit(callableBody3)
    )
}

inline fun <A, B, C, D> sync4(
    noinline callableBody1: () -> A,
    noinline callableBody2: () -> B,
    noinline callableBody3: () -> C,
    noinline callableBody4: () -> D
): Result4<A, B, C, D> {
    return Result4(
        DOC_EXECUTOR.submit(callableBody1).get(),
        DOC_EXECUTOR.submit(callableBody2).get(),
        DOC_EXECUTOR.submit(callableBody3).get(),
        DOC_EXECUTOR.submit(callableBody4).get()
    )
}

inline fun <A, B, C, D> async4(
    noinline callableBody1: () -> A,
    noinline callableBody2: () -> B,
    noinline callableBody3: () -> C,
    noinline callableBody4: () -> D
): Future<Result4<A, B, C, D>> {
    return DOC_EXECUTOR.submit<Result4<A, B, C, D>> {
        sync4(callableBody1, callableBody2, callableBody3, callableBody4)
    }
}

inline fun <A, B, C, D, E> sync5(
    noinline callableBody1: () -> A,
    noinline callableBody2: () -> B,
    noinline callableBody3: () -> C,
    noinline callableBody4: () -> D,
    noinline callableBody5: () -> E
): Result5<A, B, C, D, E> {
    return Result5(
        DOC_EXECUTOR.submit(callableBody1).get(),
        DOC_EXECUTOR.submit(callableBody2).get(),
        DOC_EXECUTOR.submit(callableBody3).get(),
        DOC_EXECUTOR.submit(callableBody4).get(),
        DOC_EXECUTOR.submit(callableBody5).get()
    )
}

inline fun <A, B, C, D, E> async5(
    noinline callableBody1: () -> A,
    noinline callableBody2: () -> B,
    noinline callableBody3: () -> C,
    noinline callableBody4: () -> D,
    noinline callableBody5: () -> E
): Future<Result5<A, B, C, D, E>> {
    return DOC_EXECUTOR.submit<Result5<A, B, C, D, E>> {
        sync5(callableBody1, callableBody2, callableBody3, callableBody4, callableBody5)
    }
}

inline fun <A, B, C, D, E, F> sync6(
    noinline callableBody1: () -> A,
    noinline callableBody2: () -> B,
    noinline callableBody3: () -> C,
    noinline callableBody4: () -> D,
    noinline callableBody5: () -> E,
    noinline callableBody6: () -> F
): Result6<A, B, C, D, E, F> {
    return Result6(
        DOC_EXECUTOR.submit(callableBody1).get(),
        DOC_EXECUTOR.submit(callableBody2).get(),
        DOC_EXECUTOR.submit(callableBody3).get(),
        DOC_EXECUTOR.submit(callableBody4).get(),
        DOC_EXECUTOR.submit(callableBody5).get(),
        DOC_EXECUTOR.submit(callableBody6).get()
    )
}

inline fun <A, B, C, D, E, F> async6(
    noinline callableBody1: () -> A,
    noinline callableBody2: () -> B,
    noinline callableBody3: () -> C,
    noinline callableBody4: () -> D,
    noinline callableBody5: () -> E,
    noinline callableBody6: () -> F
): Future<Result6<A, B, C, D, E, F>> {
    return DOC_EXECUTOR.submit<Result6<A, B, C, D, E, F>> {
        sync6(callableBody1, callableBody2, callableBody3, callableBody4, callableBody5, callableBody6)
    }
}
