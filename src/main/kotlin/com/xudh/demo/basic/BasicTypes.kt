package com.xudh.demo.basic
/**
* @Param
* @description   基本类型操作
* @return
* @author xudh
* @date 2021/6/29 22:32
*/
fun main() {
    //自动识别为Int类型
    var a = 2
    //L必须大写
    var c=10333L
    //自动识别为Double类型
    var d=3.0
    val e:Int =3
    //不能隐式转换，必须使用toLong转换
    c=e.toLong()
    var f=1f
    var g:Double=f.toDouble()

    //注意！
    var b = "helloKotlin"
    var bb=String("helloKotlin".toCharArray())
    //比较的是内容，等价于java的equals。java的==是比较引用地址
    println(b==bb)
    //比较对象是否是同一个对象，等价于java的==
    print(b===bb)
    //字符串模板
    println("字符串模板测试${a}")
}