package com.example.httpdemo.kotlin

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.httpdemo.databinding.ActivityMainBinding

/**
 * @TODO:
 * @Date: 2021/5/12 17:09
 * @User: lay
 */
//Retrofit 部分
// 依赖+权限——Api + Data Class + Utils + 异步框架配合 + Retrofit 数据解析 + Retrofit 自定义的功能如何使用

// 0 - 依赖：官网GitHub看最新版本，权限：上网权限即可。
// 1- 通常场景的使用；【√】
// 2- 异步和同步的写法；【√】
// 3- 不同的数据解析；
// 4- Retrofit 和 协程 的结合；
// 5 - 看视频 - 分析Retrofit的原理 - Retrofit 自定义的功能如何使用

//OKHttp 部分
// 0 - 依赖：官网GitHub看最新版本，权限：上网权限即可。
class BasicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}