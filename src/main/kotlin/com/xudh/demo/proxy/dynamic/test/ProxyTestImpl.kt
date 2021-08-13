package net.canway.devops.common.starter.utils.test

import com.xudh.demo.proxy.async.asyncProxy
import java.lang.Thread.sleep


open class ProxyTestImpl:ProxyTest {
    override fun test():List<String>{
        sleep(1000)
//        println("1111")
        val list = ArrayList<String>()
        list.add("111")
        return list
    }
}

fun getProxy(): ProxyTestImpl {
    Thread.sleep(3000)
    return ProxyTestImpl()
}

fun main() {
    val start = System.currentTimeMillis()

    var asyncProxy = asyncProxy {
        getProxy().test()
    }
    val test = getProxy().test()
    println((System.currentTimeMillis() - start) / 1000)

//    var abstractProxyTest = asyncProxy {
//        var abstractProxyTest: AbstractProxyTestImpl = AbstractProxyTestImpl()
//        abstractProxyTest
//    }
//    abstractProxyTest.test()
}
