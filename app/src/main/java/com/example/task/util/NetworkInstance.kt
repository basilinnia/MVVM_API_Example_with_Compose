package com.example.task.util

import com.example.task.data.NewsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkInstance {
    private const val BASE_URL = "https://newsapi.org/v2/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: NewsApi by lazy {
        retrofit.create(NewsApi::class.java)
    }
}
