package com.xudh.demo.generic


fun main() {
    val myObject:Source<Any> = MyObject()
    val myString:Source<String>  = MyString()
    val o: Source<Any>  = myString //子转父
//    val s: Source<String> = myObject //编译异常，父转子


}