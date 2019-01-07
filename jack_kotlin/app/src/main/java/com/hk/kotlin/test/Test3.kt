package com.hk.kotlin.test

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
//    testIn()
//    testMap()
//    notNull()
//    testResource()
    testNotNull()
}

//基础习惯用法

//使用区间
//for (i in 1..100) { …… }  // 闭区间：包含 100
//for (i in 1 until 100) { …… } // 半开区间：不包含 100
//for (x in 2..10 step 2) { …… }
//for (x in 10 downTo 1) { …… }
//if (x in 1..10) { …… }
fun testIn(){
    var  list = listOf(1,2,3,4,5)

    for (i in 1..10){
        println(i)
    }
    //打印结果123456789 10

    for (i in 1 until 10){
        println("***"+i)
    }
    //打印结果 123456789

    for (i in 2..10 step 3){
        if (i > 4){
            println("--- "+i)
//            break   结束当前循环
//            continue  继续当前循环
            return   //结束整个方法，后面的循环也不会执行
        }
        println("--- "+i)
    }
    //区间 2-10 ，每隔3位
    //打印结果  2 5 8

    for (i in 10 downTo 1){
        println("++++"+i)
    }
    //打印结果 10 987654321
}

//map
fun testMap(){
    val  map = mapOf("a" to 1,"2" to 2,"3" to 3)
    var map1 = mutableMapOf<String,String>()
    map1.put("北京","toly")
    map1.put("上海","vava")
    map1.put("深圳","jack")
    println(map)
    for ((k,v) in map){
        println("$k == $v")
    }
    map1.forEach { k, s -> println("$k  --- $s")}


    var map2 = mutableMapOf("ddd" to  "jack" ,"1" to  "zeze")
    //这个方法的意思是，指定的键在Map中不存在,将添加一个元素
//    map2.putIfAbsent("ddd","jackjack")
//    map2.putIfAbsent("ttt","toly")

    map1.putAll(map2)
    for ((k,v) in map1){
        println("$k &&&& $v")
    }

    println(map1["深圳"])

    println(" %%%% ${map1["深圳"]}"  )
}

fun notNull (){
    //这是不为空  not null 缩写
    val s : String = ""
    println(s?.length ?: "Empty")

    //这个 not null and else 缩写
    val files = File("Test").listFiles()
    println(files?.size ?: "empty")

//    if null 执行一个语句
//    val values = mapOf("email" to "jack")
    val values = mapOf<String,String>()
    val note = values["email"] ?: throw IllegalStateException("Email is missing!")
    val email = values["email"] ?: println("Email is missing")
}

//文件读取
fun testResource(){
//    val stream = Files.newInputStream(Paths.get("/some/file.txt"))
//    val stream = Files.newInputStream(Paths.get("C:\\Users\\12497\\Desktop\\test.txt"))
//    val stream = Files.newInputStream(Paths.get("E:\\git_kotlin\\jack_kotlin\\app\\src\\main\\assets\\test.json"))

    val stream = Files.newInputStream(Paths.get("file:///android_asset/test.json"))
    stream.buffered().reader().use { reader ->
        println(reader.readText())
    }
}

fun sumInt(a:Int ,b:Int) = a+b

fun sumTT() :Boolean = 5 > 4

fun testNotNull(){
    //!! 操作符
    //第三种选择是为 NPE 爱好者准备的：非空断言运算符（!!）将任何值转换为非空类型，若该值为空则抛出异
//    ?: 如果左侧的值为空，就取右侧的值（elvis 操作符）
//    :: 创建一个成员引用或者一个类引用
//    .. 创建一个区间

    println(sumInt(1,2))

    println(::sumInt)

    println(::sumTT)

//    val numbers = listOf(1, 2, 3)
//    println(numbers.filter(::isOdd))
//    结果[1, 3]

    fun isOdd(x: Int) = x % 2 != 0
    fun isOddd(s: String) = s == "brillig" || s == "slithy" || s == "tove"

    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd)) // 引用到 isOdd(x: Int)

    val predicate: (String) -> Boolean = ::isOddd   // 引用到 isOdd(x: String)
    println(predicate)

    println(lock("param1", "param2", ::getResult))
}

/**
 * @param str1 参数1
 * @param str2 参数2
 */ fun getResult(str1: String, str2: String): String = "result is {$str1 , $str2}" /**
 * @param p1 参数1
 * @param p2 参数2
 * @param method 方法名称
 */ fun lock(p1: String, p2: String, method: (str1: String, str2: String) -> String): String { return method(p1, p2) }


