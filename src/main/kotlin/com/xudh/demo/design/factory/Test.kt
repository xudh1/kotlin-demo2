package com.xudh.demo.design.factory

fun main() {
    val factoryA:Factory = FactoryA()
    val factoryB:Factory = FactoryB()
    val makeProductA = factoryA.makeProduct()
    val makeProductB = factoryB.makeProduct()
    println(makeProductA.name)
    println(makeProductB.name)
}