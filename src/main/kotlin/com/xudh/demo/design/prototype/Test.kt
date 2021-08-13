package com.xudh.demo.design.prototype

fun main() {
    val mail = EMail("abc@example.com", "Hello", "Don't know what to write.")
    val copy = mail.copy(recipient = "other@example.com")
    println(mail.toString())
    println(copy.toString())

}
