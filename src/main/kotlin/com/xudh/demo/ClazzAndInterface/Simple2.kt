package com.xudh.demo.ClazzAndInterface

class Simple2:Simple(), ISimple,ISimple2 {

    //重写方法必须有override
    override fun methodCanOverride(a: Int, b: Int) {
        super.methodCanOverride(a, b)
        println("methodCanOverride2,$a,$b")
    }

    override fun SimpleMethodInInterface() {
        println("SimpleMethodInInterface2")
    }

    override fun SimpleMethodInInterface2() {
        println("SimpleMethodInInterface22")
    }
}

fun main() {
    //调用的是父类的方法
    Simple2().abstractMethod()
    Simple2().methodCanOverride(1,2)
    Simple2().SimpleMethodInInterface()
    Simple2().SimpleMethodInInterface2()
}