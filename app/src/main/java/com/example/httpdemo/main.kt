package com.example.httpdemo

import kotlinx.coroutines.*
import kotlin.concurrent.thread

//coroutines - 这个是协程库
//定义：本质上，协程是轻量级别的线程，协程在使用上更像一个线程池子。
// 线程的框架（API）- Kotlin 基于JVM
// 为什么不直接使用Java线程？
// - 痛点一：回调地狱。编码风格

//Cooperation 合作
//Routine 日常，常规事务

fun main01(){
    //功能 = 开辟子线程
    // GlobalScope 是啥？初级：这个是一个只受到整个程序生命周期的影响。
    // 作用域构建器 - CoroutinesScope
    thread {
        Thread.sleep(1000)
        println("子线程")
    }

    GlobalScope.launch {
        //delay 是一个特殊的挂起函数，不会导致线程阻塞
        //delay 会挂起协程，只能在协程使用。
        delay(1000)
        println("Global")
    }

    println("hello,main")
    //1 -
    // Thread.sleep(2000)

    //2 -
    // 阻塞
    runBlocking {
        delay(2000)
    }

}

//3 - 桥接阻塞和非阻塞
// join()挂起函数
//4 - 等待一个作业
fun main02() = runBlocking<Unit>{
    val job = GlobalScope.launch {
        delay(1000L)
        println("2")
    }
    println("1,")
    job.join()
}

//5-简化写法，作用域写法,结构化的并发
//外部执行完了，才会去执行内部的协程。
// 内部协程看情况，像是按顺序串行执行。
// 实际上都是并行执行
fun main03() = runBlocking<Unit>{
    launch {
        delay(1000L)
        println("2")
    }
    launch {
        delay(1000L)
        println("3")
    }
    launch {
        delay(1000L)
        println("4")
    }
    println("1")
}

//作用域构建器 - CoroutineScope
//GlobalScope - 全局构建器 - 应用程序生命周期结束才结束。
//runBlocking - 先执行自己的，再执行launch，他会阻塞当前线程来等待。
// 自己的部分是按顺序执行。
// launch的部分是并行执行
fun main() = runBlocking{
    launch {
        delay(1000L)
        println("2 - 1")
    }
    launch {
        delay(1000L)
        println("2 - 2")
    }
    launch {
        delay(1000L)
        println("2 - 3")
    }
    launch {
        delay(1000L)
        println("2 - 4")
    }
    println("4 - 1")
    //coroutineScope 是挂起函数，runBlocking 是常规函数，会阻塞。
    coroutineScope {
        launch {
            delay(1000L)
            println("3")
        }
        delay(1000L)
        println("1")
    }
    println("4")
}

//线程的数据都在内存中。

// Java 多线程开发 - join

//关键点：什么是挂起？什么是阻塞？
// 回答：
// Thread.sleep 是阻塞。delay 是阻塞
// 定义：挂起 - 一般是主动的。（不释放CPU，可能释放内存，放在外存）
// - 车子卸货，车还在开，有可能在拖其他货。
// 挂起，还能做其他事情。bu'xian'c阻塞只能等。
// 挂起更高效的提升了使用效率。

// 定义：阻塞 - 一般是被动的。在抢占资源中得不到资源。被动的挂起在内存，等待资源将他唤醒。
// 释放CPU，不释放内存。
// - 货没有卸下，车子停下 ，等红绿灯，开车离开。

//问：那挂起后再恢复是不是就可能不是之前的线程执行？

//问：阻塞先于挂起执行？
// 回答：目前是这样的。

//问：协程为什么需要主线程等待更多的时间呢？
// 回答：因为协程依附于主线程，就好比子线程依附于主线程。只是比子线程更加敏感。

//问：线程切换框架和异步框架的区别是什么？

//复习：
// 1 - 匿名函数的拓展函数