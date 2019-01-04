package com.hk.kotlin.test

fun main(args: Array<String>) {

//test2()
//testAlso()
//testLet()
    testApply()
}

//Kotlin - let, apply, with, run的差别和用法
//使用作用域函数 apply/with/run/also/let
//需要注意上下文，用法都区别不大
//函数名 	定义inline的结构 	函数体内使用的对象 	返回值 	是否是扩展函数 	适用的场景
//let 	fun T.let(block: (T) -> R): R = block(this) 	it指代当前对象 	闭包形式返回 	是 	适用于处理不为null的操作场景
//with 	fun with(receiver: T, block: T.() -> R): R = receiver.block() 	this指代当前对象或者省略 	闭包形式返回 	否 	适用于调用同一个类的多个方法时，可以省去类名重复，直接调用类的方法即可，经常用于Android中RecyclerView中onBinderViewHolder中，数据model的属性映射到UI上
//run 	fun T.run(block: T.() -> R): R = block() 	this指代当前对象或者省略 	闭包形式返回 	是 	适用于let,with函数任何场景。
//apply 	fun T.apply(block: T.() -> Unit): T { block(); return this } 	this指代当前对象或者省略 	返回this 	是 	1、适用于run函数的任何场景，一般用于初始化一个对象实例的时候，操作对象属性，并最终返回这个对象。
//2、动态inflate出一个XML的View的时候需要给View绑定数据也会用到.
//3、一般可用于多个扩展函数链式调用
//4、数据model多层级包裹判空处理的问题
//also 	fun T.also(block: (T) -> Unit): T { block(this); return this } 	it指代当前对象 	返回this 	是 	适用于let函数的任何场景，一般可用于多个扩展函数链式调用
//---------------------
//作者：mikyou
//来源：CSDN
//原文：https://blog.csdn.net/u013064109/article/details/78786646
//版权声明：本文为博主原创文章，转载请附上博文链接！

// 上下文对象是“it”
class User ( var name:String ,var age :Int ){
    init {
        println("init jack")
    }

    fun mofify( x: String, y: Int) {
        name = "Gem_"
        age = y + 1
    }

//    fun modifyAge( age:Int, a :Int):Int = age + a
    fun modifyAge( baseAge:Int, a :Int):Int {
        age = baseAge +a
        return age
    }
}

fun test1 (){
    var user = User("jack",22)
    user.mofify(user.name,user.age)

    user.apply {
        user.mofify("",25)
        user.modifyAge(user.age,3)     //29
//        println("apply----  ${user.name },${user.age}")
        //this 上下文，可以直接取值
        println("apply----  $name,$age")
    }
    user.also {
        it.modifyAge(it.age,4)      //29+4 33
        println("also ---- ${it.name},${it.age}")
        //不是上下文，不能直接取值
//        println("also ---- $name,$age")
    }
    user.let {
        user.modifyAge(it.age,5)    //33+5 38
//        println("let ---- ${user.name},${it.age}")
        //不是this 上下文，不能直接取值
//        println("let ---- $name,$age")
    }
    user.run {
        user.modifyAge(user.age,6)  //38+6 44
//        println("run ---- ${user.name},${user.age}")
        //可以直接取值
        println("run ---- $name,$age")
    }
    with(user){
        user.modifyAge(user.age,7)
//        println("with ---- ${user.name},${user.age}")
        //可以直接取值
        kotlin.io.println("$name,$age")
    }
}


fun test2(){
    var user = User("jack",22)
    user.mofify(user.name,user.age)
    user.also {
        it.modifyAge(it.age,2)
        println("also **** ${it.age}")
    }.apply {
        user.modifyAge(user.age,2)
        println("apply **** $age")
    }.let {
        it.modifyAge(it.age,2)
        println("let ***** ${it.age}")
    }.run {
        user.modifyAge(user.age,2)
        println("run **** ${user.age}")
    }
}

fun testAlso(){
    var user = User("jack",22)
    user.mofify(user.name,user.age)
    user.also {
        it.modifyAge(it.age,2)
        println("also******")
        println(" **** ${it.age}")
    }.also {
        user.modifyAge(user.age,2)
        println(" **** ${it.age}")
    }.also {
        it.modifyAge(it.age,2)
        println(" ***** ${it.age}")
    }.also {
        user.modifyAge(user.age,2)
        println("**** ${user.age}")
    }
}

fun testLet(){
    var user = User("jack",22)
    user.mofify(user.name,user.age)
    user.let {
        it.modifyAge(it.age,2)
        println("let******")
        println(" **** ${it.age}")
    }.let {
        user.modifyAge(user.age,2)
        println("**** ${user.age}")
    }.let {
        user.modifyAge(user.age,2)
        println("**** ${user.age}")
    }.let {
        user.modifyAge(user.age,2)
        println("**** ${user.age}")
    }
}


fun testApply(){
    var user = User("jack",22)
    user.mofify(user.name,user.age)
    user.apply {
        user.modifyAge(user.age,2)
        println("apply ******")
        println(" **** ${user.age}")
    }.apply {
        user.modifyAge(user.age,2)
        println("**** ${user.age}")
    }.apply {
        user.modifyAge(user.age,2)
        println("**** ${user.age}")
    }.apply {
        user.modifyAge(user.age,2)
        println("**** ${user.age}")
    }
}