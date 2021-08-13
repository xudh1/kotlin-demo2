package com.xudh.demo.basic

/**
 * @Param
 * @description 区间的操作
 * @return
 * @author xudh
 * @date 2021/6/29 23:19
 */
fun main() {

    //可数
    val intRange = 1..10 //[1,10]
    val intRangeExclusive = 1 until 10 //[1,10)
    val intRangeReverse = 10 downTo 1 //[10,1]
    val intRangeWithStep = 1..10 step 2//带步长的，每个2个   1 3 5 7
    println(intRange.joinToString())
    println(intRangeExclusive.joinToString())
    println(intRangeReverse.joinToString())
    println(intRangeWithStep.joinToString())
    val charRange = "a".."z" //[a,z]
    val longRange = 1L..10L //[1L,10L]
    println(charRange)
    println(longRange.joinToString())
    //不可数，不能打印,不能用step
    var floatRange = 1f..10f
    var doubleRange = 1.0..10.0

    //结合区间，通过下标来遍历数组
    val b= charArrayOf('a','b','c','d','e')
    for (i in 0 until b.size) {
        print(b[i])
    }
    println()
    //官方已经封装更加简洁用法
    for (index in b.indices) {
        print(b[index])
    }

}