package com.xudh.demo.basic

/**
 * @Param
 * @description 数组的操作
 * @return
 * @author xudh
 * @date 2021/6/29 23:18
 */
fun main() {
    // int类型数字创建，创建固定大小数组，非包装类型创建方式
    val a = IntArray(5)
    // 不同于java，java使用的关键字是length
    println(a.size)

    var aa = ArrayList<Int>()
    // 数组新增元素
    aa.add(1)
    println(aa.size)

    // 初始化数组
    val b = intArrayOf(1, 2, 3, 4, 5)
    println(b.contentToString())
    println(b.reverse())
    println(b.contentToString())
    // t为数组下标
    val bb = IntArray(5) { it }
    println(bb.contentToString())
    // lamdam表达式创建数组
    val bbb = IntArray(5) { (it + 1) * 2 }
    println(bbb.contentToString())
    // 修改数组某个值 setter
    bbb[0] = 99
    // 获取数组某个值 getter
    println(bbb[0])
    println(bbb[2])

    var f = floatArrayOf(1f, 2f, 3f, 4f)
    // 数组的遍历 java中是 ：
    for (fl in f) {
        println(fl)
    }
    // labdam表达式遍历方式
    f.forEach { t -> println(t) }

    // 数组值是否包含某个判断
    if (1f in f) {
        println("数组f中包含1.0f值")
    }
    // 数组值是否包含某个判断
    if (6f !in f) {
        println("数组f中不包含6.0f值")
    }
}
