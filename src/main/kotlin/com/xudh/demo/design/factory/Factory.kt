package com.xudh.demo.design.factory

/**
* @Param
* @description   工厂方法模式(每个实现类生产不同产品)
* @return
* @author xudh
* @date 2021/7/5 9:20
*/
interface Factory {
    fun makeProduct(): Product
}