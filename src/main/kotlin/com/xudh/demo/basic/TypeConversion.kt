package com.xudh.demo.basic

import com.xudh.demo.ClazzAndInterface.AbstractSimpleClass
import com.xudh.demo.ClazzAndInterface.Simple

fun main() {
    val simple = Simple()
    //类型判断完后，kotlin能够智能转换为判断的类，不用像java需要强制转换
    //只能强制转换时不能是共享变量，因为多线程情况下，可能被其他线程修改了变量
    if (simple is AbstractSimpleClass) {
        println(simple.a)
    }

    var s:String?="sss"
    //判断为空时也会智能转换
    //声明为val的话，参数就不可变，也就不会受多线程影响智能转换
    if (s != null) {
        println(s.length)
    }

}