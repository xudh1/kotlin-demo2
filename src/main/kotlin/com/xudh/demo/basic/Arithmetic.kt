package com.xudh.demo.basic

/**
 * @Param
 * @description 四则运算符
 * @return
 * @author xudh
 * @date 2021/6/30 14:59
 */
fun main(vararg args: String) {
    if (args.size < 3) {
        showHelp()
    }

//    val operators = mapOf(
//        "+" to ::plus,
//        "-" to ::reduce,
//        "*" to ::Multiply,
//        "/" to ::division
//    )

    //声明一个hashmap
    val operators= HashMap<String, Function2<Double, Double, Double>>()
    operators.put("+", ::plus)
    operators.put("-", ::reduce)
    operators.put("*", ::Multiply)
    operators.put("/", ::division)


    val operator = operators[args[1]] ?: return showHelp()
    var result=0.0
    try {
        result = operator(args[0].toDouble(), args[2].toDouble())
    } catch (e:Exception) {
        showHelp()
    }
    println("输入：${args.joinToString(" ")}")
    println("计算结果：${result}")
}
fun plus(arg0: Double, arg1: Double): Double {
    return arg0 + arg1
}

fun reduce(arg0: Double, arg1: Double): Double {
    return arg0 - arg1
}

fun Multiply(arg0: Double, arg1: Double): Double {
    return arg0 * arg1
}

fun division(arg0: Double, arg1: Double): Double {
    return arg0 / arg1
}

fun showHelp() {
    println("参数格式错误！")
}
