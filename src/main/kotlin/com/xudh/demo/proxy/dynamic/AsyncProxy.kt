package net.canway.devops.common.starter.utils.proxy

import com.xudh.demo.proxy.async.DOC_EXECUTOR
import java.util.concurrent.Future

abstract class AsyncProxy<T>(
    body: () -> T
) {
    // 将 Lambda 表达式封装成 AsyncCallable 放入到线程池进行一步调用
    var asyncTask: Future<T> = DOC_EXECUTOR.submit(body)

    protected fun getObject(): Any? {
        return asyncTask.get()
    }
}