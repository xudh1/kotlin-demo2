package com.xudh.demo.ClazzAndInterface

abstract class AbstractSimpleClass {

    val a="aaa"
    abstract fun abstractMethod()

    //不能重写
    fun method() {
    }

    //重写必须有open关键字
    open fun methodCanOverride(a:Int,b:Int) {
        println("$a,$b")
    }
}