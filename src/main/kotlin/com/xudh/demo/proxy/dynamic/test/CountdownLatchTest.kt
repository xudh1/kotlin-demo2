package com.xudh.demo.proxy.dynamic.test

import net.canway.devops.common.starter.utils.test.ProxyTestImpl
import net.canway.devops.common.starter.utils.test.ProxyTestImpl2
import net.canway.devops.common.starter.utils.test.ProxyTestImpl3
import java.lang.Thread.sleep
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

class CountdownLatchTest {

}

fun main() {
    val newFixedThreadPool = Executors.newFixedThreadPool(3)
    val latch =  CountDownLatch(3)

    //无返回值
    val submit = newFixedThreadPool.submit {
        println("${Date()}子线程${Thread.currentThread().name}开始执行")
        sleep(1000)
        val test = ProxyTestImpl().test()
        println("${Date()}子线程${Thread.currentThread().name}执行完成")
        latch.countDown();
    }
    val get = submit.get()
    println(get)
    newFixedThreadPool.submit {
        println("${Date()}子线程${Thread.currentThread().name}开始执行")
        sleep(2000)
        ProxyTestImpl2().test()
        println("${Date()}子线程${Thread.currentThread().name}执行完成")
        latch.countDown();
    }
    newFixedThreadPool.submit {
        println("${Date()}子线程${Thread.currentThread().name}开始执行")
        sleep(3000)
        ProxyTestImpl3().test()
        println("${Date()}子线程${Thread.currentThread().name}执行完成")
        latch.countDown();
    }



}