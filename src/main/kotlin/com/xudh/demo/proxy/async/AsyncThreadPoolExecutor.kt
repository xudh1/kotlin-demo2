package com.xudh.demo.proxy.async

import com.xudh.demo.proxy.exception.AsyncStackRecordException
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger

class AsyncThreadPoolExecutor(
    name: String,
    corePoolSize: Int,
    maximumPoolSize: Int,
    keepAliveTime: Long,
    unit: TimeUnit,
    workQueue: BlockingQueue<Runnable>
) : ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, AsyncThreadFactory(name)) {
    override fun submit(task: Runnable): Future<*> {
        return FutureWrapper(super.submit(RunnableWrapper(task)))
    }

    override fun <T : Any?> submit(task: Runnable, result: T): Future<T> {
        return FutureWrapper(super.submit(RunnableWrapper(task), result))
    }

    override fun <T : Any?> submit(task: Callable<T>): Future<T> {
        return FutureWrapper(super.submit(CallableWrapper(task)))
    }

    override fun execute(command: Runnable) {
        super.execute(RunnableWrapper(command))
    }

    private inner class FutureWrapper<V>(private val future: Future<V>) : Future<V> {
        override fun isDone(): Boolean = future.isDone

        override fun get(): V {
            try {
                return future.get()
            } catch (e: ExecutionException) {
                throw getException(e)
            }
        }

        override fun get(timeout: Long, unit: TimeUnit): V {
            try {
                return future.get(timeout, unit)
            } catch (e: ExecutionException) {
                // 捕获FutureTask抛出的ExecutionException
                throw getException(e)
            }
        }

        override fun cancel(mayInterruptIfRunning: Boolean): Boolean = future.cancel(mayInterruptIfRunning)

        override fun isCancelled(): Boolean = future.isCancelled

        private fun getException(e: ExecutionException): Throwable {
            // 抛出非 RuntimeException, 保存异步堆栈信息
            return AsyncStackRecordException(e.cause!!)
        }
    }

    private inner class RunnableWrapper(
        private val runnable: Runnable
    ) : Runnable {
        private val docRequestContext: InheritableRequestContext? = InheritableRequestContextHolderProvider.getRequestContext()

        override fun run() {
            try {
                docRequestContext?.let {
                    InheritableRequestContextHolderProvider.setRequestContext(docRequestContext)
                }
                runnable.run()
            } finally {
                InheritableRequestContextHolderProvider.removeRequestContext()
            }
        }
    }

    private inner class CallableWrapper<V>(
        private val callable: Callable<V>
    ) : Callable<V> {
        private val docRequestContext: InheritableRequestContext? = InheritableRequestContextHolderProvider.getRequestContext()

        override fun call(): V {
            try {
                docRequestContext?.let {
                    InheritableRequestContextHolderProvider.setRequestContext(docRequestContext)
                }
                return callable.call()
            } finally {
                InheritableRequestContextHolderProvider.removeRequestContext()
            }
        }
    }

    class AsyncThreadFactory(
        name: String
    ) : ThreadFactory {
        private val group: ThreadGroup
        private val threadNumber = AtomicInteger(1)
        private val namePrefix: String

        init {
            val s = System.getSecurityManager()
            group = if (s != null) s.threadGroup else Thread.currentThread().threadGroup
            namePrefix = "async-pool-$name-thread-"
        }

        override fun newThread(r: Runnable): Thread {
            val t = Thread(
                group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0
            )
            if (t.isDaemon) t.isDaemon = false
            if (t.priority != Thread.NORM_PRIORITY) t.priority = Thread.NORM_PRIORITY
            return t
        }
    }
}
