package com.xudh.demo.basic

fun main() {
    //不可变list，不能add和remove
    val intList:List<Int>
    intList=listOf(1,2,3)
    //可变list，能add和remove
    val intList2:MutableList<Int>
    intList2=mutableListOf(1,2,3)
    intList2.remove(2)
    println(intList2
    )
    val map:Map<String,Any>
    map= mapOf("name" to "benny","age" to 24)
    for (entry in map) {
        //注意获取map值的写法
        println("${entry.key}=${entry.value}***${map["name"]}:${map["age"]}")
    }

    //pair用法
    val pair = Pair("aaa", "bbb")
    val (x,y)=pair
    println(x+y)
    println(pair.first+pair.second)
    //Triple用法
    val triple = Triple("aaa", "bbb","ccc")
    val (x1,y1,z1)=triple
    println(x1+y1+z1)
    println(triple.first+triple.second+triple.third)

    var list = ArrayList<Int>()
    list.add(1)
    println(list)
    var persons = ArrayList<Person>()
    persons.add(Person("xudh", 24))
    for (person in persons) {
        println(person.name)
    }
}