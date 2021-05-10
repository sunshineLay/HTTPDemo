package com.example.httpdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.httpdemo.databinding.ActivityMainBinding

//viewBinding - 官方推荐
//anko - 可以了解一下
//直接写id - 这个插件已经deprecated了，不学习

//应用开发热点：
//Kotlin - 协程 - coroutines
//JetPack

//Kotlin coroutines , 是协程的异步框架
//RxJava , 是线程的异步框架

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}