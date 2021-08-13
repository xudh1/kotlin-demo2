package com.xudh.demo.design.abstractFactory

/**
* @Param
* @description 抽象工厂设计模式（生产统一品牌一系列不同产品）
* @return
* @author xudh
* @date 2021/7/5 9:48
*/
interface AbstractFactory {
    val context: Context?
    fun makeTextView(): TextView
    fun makeButton(): Button
}