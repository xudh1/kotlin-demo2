package com.xudh.demo.basic

fun main() {
    //? 关键字可空
    val nullableStr:String?=null
    //?.可空，安全访问运算符
    val length=nullableStr?.length
    println(nullableStr)
    println(length)


    val nullableStr2:String?=null
    //?；为空时为0
    val length2=nullableStr?.length?:0
    println(nullableStr2)
    println(length2)

    //!! 强转不能为空，为空则抛出空指针
    val nullableStr1:String?="www"
    val length1=nullableStr!!.length
    println(nullableStr1)
    println(length1)


}