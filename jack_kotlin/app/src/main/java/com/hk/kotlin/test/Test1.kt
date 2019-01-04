package com.hk.kotlin.test

import java.lang.Integer.parseInt

fun main(args: Array<String>) {
    print(sum(5, 6))
    println(sum1(1, 1))
    println(tt(2, 3))
    param()
    println("x = $x ,pi = $pi")
    println(increaseX())
    println("x = $x ,pi = $pi")
    printStr()
    test("1", "5")
    test1("0", "5")
    var obj = 56789
    getObjToStr(obj)
    forList()
    whileList()
    descrip(0)
    descrip("jack")
    descrip(222222222222222)
    checkItem()
}

//定义函数  返回Int的函数
fun sum(x :Int, y:Int): Int {
    return x+y
}

//将表达式作为函数体、返回值类型自动推断的函数：
fun sum1 (a:Int ,b:Int) = a+b

//函数返回无意义的值：Unit 可以加也可以不加
fun tt(a: Int,b: Int) : Unit{
    println(a+b)
}

//定义只读局部变量使用关键字 val 定义。只能为其赋值一次。
//可重新赋值的变量使用 var 关键字：
fun param(){
    var a = 3
    val b:Int = 4
    var c :Int
//    b = 6 // 会报错，reassigned 重分配，再分配
    c = 5
    println("a=$a ,b=$b,c=$c ")
}

//顶层变量：
val pi = 3.14
var x = 0

fun increaseX(){
    x += 1

//    x++
    println(x)
}

//字符串模板
// $跟上字符串或者用花括号括起来的任意表达式: 特殊美元符号
fun printStr() : String? { //返回的地方加上？,表示可以为空，不加不能为空
    var jack = 555
    var ze = "zhe"
    val shu = "shu"
    println("$jack,${ze.length},$shu. length is ${shu.length},**900 ${'$'}**")
    println("${jack.toLong()}")
    return null
}

//if 条件表达式
fun maxParam(a:Int,b:Int): Int {
    if (a >b){
        println(a)
        return a
    }else{
        print(b)
        return b
    }
}

fun maxParam (a: Int,b: Int,c:Int) = if(a >b) a else b

//fun maxParam1(a: Int,b: Int )= (a>0)? a:b
//kotlin中不再有Java的三目运算 取代的是 if(a >b) a else b


//使用可空值及 null 检测
//当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空。
fun test(a: String,b: String):Int{
    val x = parseInt(a)
    val y = parseInt(b)
    if (x != null && y != null){
        println(x*y)
        return x*y
    }else{
        println("$a 可能是空，$b 也可能是空")
        return 1
    }
}

fun test1(a: String,b: String):Int?{
    val x = parseInt(a)
    val y = parseInt(b)
    return null
}

//is 检测类型转换  obj is String 类型监测的时候，满足条件时，类型就自动转换了
fun getObjToStr(obj :Any): Int?{
//    if (obj is String) return obj.length

    if (obj is String){
        println(obj)
        return obj.length
    }else{
        println("这是一个非字符串")
    }
    return null
}

//使用for 循环
fun forList(){
    //创建方式1
    var list:List<String> = ArrayList()
    //创建方式2
    var aList = listOf<String>()
    //创建方式3
    val bList = listOf("a",1,"one")

    //循环方式1
    for (i in bList){
        println( "******"+i)
    }
    //循环方式2
    bList.forEach {
        println("-----"+it+"----")
    }
    //循环方式3
    for (index in bList.indices){
        println("第 $index 个是 ${bList[index]} ")
    }
}

// while 循环
fun whileList(){
    //list 这种创建方式是不可变的集合list
    val list = listOf<String>()
    list.plus("tt")
    println("list的长度是${list.size}")
    list.forEach { println("+++++++ $it ++++") }
    list.minus("tt")
    list.forEach { println("------- $it ---") }

    //mutableListOf  这种是可变的集合
    var mList = mutableListOf<String>()
//    var mList = mutableListOf("1",4,8)
    mList.add("ttt")
    mList.add("11")
    mList.forEach {
        println("add++++++++$it+++++")
    }
    mList.removeAt(1)
    mList.forEach {
        println("remove++++++++$it+++++")
    }

    var nList = mutableListOf("333",555,222,"ttt")
    nList.add(5)
    nList.add("jack")
    nList.forEach {
        println("----- $it +++++")
    }
    var index = 0
    while (index < nList.size){
        println("while循环 第$index 个是 ${nList.get(index)}")
        index ++
    }

    //任意类型
    var anyList = mutableListOf<Any>()
    anyList.add("jack")
    anyList.add(4444)
}

//使用when 表达式
fun descrip(obj: Any){
    when(obj){
        1-> println("1111111111")
        is Long-> println("2222222222")
        3-> println("3333333333")
//        (obj is String && obj.equals("jack")) -> println("jack")
//        (obj is String && obj.contentEquals("jack")) -> println("jack")
        is String  -> println("jack")
        else  -> println("unknown")
    }
}

//使用区间（range）
//使用 in 运算符来检测某个数字是否在指定区间内：
//使用 in 运算符来判断集合内是否包含某实例：
fun checkItem(){
    val a = 5
    if (a in 0..10){
        println("$a is in the rang")
    }
    if (a !in 6..10){
        println("$a is not in the rang")
    }

    for (x in 1..5) {
        println(x)
    }
    for (x in 1..10 step 2) {
        println(x)
    }
    for (x in 9 downTo 0 step 3) {
        println(x)
    }
    for (x in 9 downTo 0 step 5) {
        println("******$x")
    }

    val list = listOf(1,2,3,4)
    when{
        5 in list -> println("555555555555555555")
        2 in list -> println("222222222222222222")
        3 in list -> println("333333333333333333")
    }

    list.filter { it > 2 }.sorted().forEach {
        println(it)
    }

    val strList = listOf("add","amount","cd","adventre")
    strList.filter { it.startsWith("a") }.sortedBy { it }.map { it.toLowerCase() }.forEach { println(it) }

}













