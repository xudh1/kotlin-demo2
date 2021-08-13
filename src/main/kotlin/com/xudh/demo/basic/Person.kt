package com.xudh.demo.basic

class Person(val name: String, val age: Int) {
    init {
        println("姓名为${name},年龄为${age}")
    }
    constructor(name: String) : this(name,24) {
    }
    val job = "java开发"
    var company: String = "Allinpay"
    var sex: String = ""
    var counter = 0 // 初始化值会直接写入备用字段
        //重写是写setter方法
        set(value) {
            if (value >= 0)
                field = value
        }
}
fun main(args:Array<String>) {
    val person = Person("徐东浩", 24)
    println("姓名:${person.name},年龄:${person.age}")
    println("岗位:${person.job}")
    println("之前公司:${person.company}")
    person.company="CanWay"
    println("现在公司:${person.
        company}")
    person.counter=-1
    println(person.counter)
    person.counter=2
    println(person.counter)
}