package com.xudh.demo.design.adapter

class Adapter(val wrapper: Adaptee) : Target {
    override fun request() {
        wrapper.ask()
    }
}