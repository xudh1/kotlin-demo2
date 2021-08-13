package com.xudh.demo.basic

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * @Param
 * @description 高阶函数
 * @return
 * @author xudh
 * @date 2021/7/1 9:39
 */

//接收两个参数，对两个参数进行处理，实际如何处理交给实际传进来的函数
fun dealNumber(a: Int, b: Int, exeFunc: (Int, Int) -> Int): Int {
    //区别
//    return exeFunc(a, b)
    return exeFunc.invoke(a,b)
}

fun dealStr(s: String, exeFunc: (String) -> String): String {
    return exeFunc.invoke(s)
}

//字符串的字符码相加求和
fun String.sumBy(exeFunc: (Char) -> Int): Int {
    var sum:Int=0
    for (c in this) {
        sum+=exeFunc(c)
    }
    return sum
}

//对没有请求参数了有返回值的函数进行加锁处理后释放锁
fun <T> lock(lock: Lock, deal: ()->T): T {
    lock.lock()
    try {
        return deal()
    }finally {
        lock.unlock()
    }
}

fun main() {
    val dealNumber = dealNumber(1, 2, { a, b -> a + b })
    println(dealNumber)

    //lambda表达式，当需要的函数类型的参数只有和一个时可以用it代替
    val dealStr = dealStr("qqq") { it+"3333" }
    println(dealStr)
    //等价于下面表达式
    val dealStr1 = dealStr("qqq") { s -> s + "3333" }
    println(dealStr1)

    //最简洁写法
    val sumBy = "abc".sumBy { it.toInt() }
    println(sumBy)
    //其他等价写法
    "abc".sumBy ({it.toInt()})
    "abc".sumBy (){it.toInt()}
    "abc".sumBy (){x->x.toInt()}
    "abc".sumBy ({x->x.toInt()})

    val lock = ReentrantLock()
    val result = lock(lock){
        println("abc")
        return@lock 1
    }
    println(result)



}
