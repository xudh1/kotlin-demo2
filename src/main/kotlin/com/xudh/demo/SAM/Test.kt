package com.xudh.demo.SAM

import java.util.function.*


fun main() {

//    Int--->Boolean
    val intPredicate = IntPredicate { it % 2 == 0 }
    val test = intPredicate.test(2)
    println(test)

//    Function
    val apply = Function { i: Int -> i * 2 }.apply(2)
    println(apply)
//    Consumer
    Consumer{s:String-> println("${s}")}.accept("323")
//    Predicate
    val test1 = Predicate { d: String -> d == "ee" }.test("ee")
    println(test1)

//    Supplier
    val get = Supplier { "getObject"}.get()
    println(get)

}