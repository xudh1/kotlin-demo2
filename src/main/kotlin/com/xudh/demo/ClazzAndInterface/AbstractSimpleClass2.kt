package com.xudh.demo.ClazzAndInterface

abstract class AbstractSimpleClass2 {
    abstract fun abstractMethod()

    fun method() {
    }

    open fun methodCanOverride(a:Int,b:Int) {
        println("$a,$b")
    }
}