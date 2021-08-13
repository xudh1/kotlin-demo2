package com.xudh.demo.proxy

fun main() {
    val baseImpl = BaseImpl()
    val derived = Derived(baseImpl)
    derived.print()
}