package com.xudh.demo.ClazzAndInterface

open class Simple:AbstractSimpleClass(), ISimple,ISimple2 {

    //重写后，通过final设置这个方法不能再被重写
    final override fun abstractMethod() {
        println("abstractMethod")
    }

    //重写方法必须有override
    override fun methodCanOverride(a: Int, b: Int) {
        super.methodCanOverride(a, b)
        println("methodCanOverride,$a,$b")
    }

    override fun SimpleMethodInInterface() {
        println("SimpleMethodInInterface")
    }

    override fun SimpleMethodInInterface2() {
        println("SimpleMethodInInterface2")
    }
}

fun main() {
    Simple().abstractMethod()
    Simple().methodCanOverride(1,2)
    Simple().SimpleMethodInInterface()
    Simple().SimpleMethodInInterface2()
}