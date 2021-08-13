package com.xudh.demo.basic

/**
* @Param
* @description 扩展方法
* @return
* @author xudh
* @date 2021/6/30 16:32
*/

fun main() {
     val s="dddd"
    val extMethodResult = s.extMethod(222)
    println(extMethodResult)
    val personExtMethod = Person("徐东浩", 24).extMethod("姓名")
    println(personExtMethod)
}

//自定义拓展方法
fun String.extMethod(i: Int): String {
    return "$i+$this"
}

fun Person.extMethod(s: String): String {
    return "$s:${this.name}"
}