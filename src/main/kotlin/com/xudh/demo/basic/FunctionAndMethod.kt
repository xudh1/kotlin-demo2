package com.xudh.demo.basic

//方法是是类里面的方法,reciever的就是类的实例
//函数没有包含在类里面的
class Foo{
    //方法
    //Any等价java的Oject
    //可以有默认参数，不传使用参数默认值值
    fun fooIsMethod(i:Int,d:Double=1.0):Any {
        println("i=${i},d=${d},fooIsMethod")
        return "aaa"
    }
    fun fooIsMethod2(i:Int,d:Double=1.0):Any {
        println("i=${i},d=${d},fooIsMethod")
        return "bbb"
    }
}

//函数
fun fooIsFunction(i:Int):Any  {
    println("i=${i},${i}fooIsFunction")
    return 999
}


//难点
//高阶函数
//函数参数接收Foo类下的参数类型为Int和Double的，返回类型为Any的方法
fun fooIsFunctionNested(p:(Foo, Int, Double) -> Any):Any  {
    val p1 = p(Foo(), 1, 3.0)
    println(p1)
    return p1
}

//变长参数关键字 vararg
fun multiParameters(vararg i: Int) {
    println(i.contentToString())
}

//Unit等价于java的void
fun main():Unit {
    //第二参数可以不传，不传则使用默认参数值，但是必须按顺序
    //如果使用参数默认值不按顺序传，则需要只有参数
    val fooIsMethod = Foo().fooIsMethod(111)
    val fooIsFunction = fooIsFunction(222)
    println(fooIsMethod)
    println(fooIsFunction)

    //函数引用
    //::表示函数指针
    val f:(Int)->Any=::fooIsFunction
    val g: (Foo, Int, Double) -> Any =
        Foo::fooIsMethod
    val h: (Int, Double) -> Any = Foo()::fooIsMethod
    println(f)
    println(g)
    println(h)

    //将符合规则的函数介绍参数规则的函数引用传递给fooIsFunctionNested
    val fooIsFunctionNested =
        fooIsFunctionNested(Foo::fooIsMethod)
    val fooIsFunctionNested2 =
        fooIsFunctionNested(Foo::fooIsMethod2)
    println("函数调用函数，${fooIsFunctionNested}")
    println("函数调用函数2，${fooIsFunctionNested2}")

    multiParameters(1, 2, 3, 4)
    //声明一个lambda表达式，也为匿名函数
    val func= { println("33333")}
    //调用函数
    func.invoke()
    func()
}
