package com.xudh.demo.generic

abstract class Source<out T> {
    abstract fun nextT(): T
}