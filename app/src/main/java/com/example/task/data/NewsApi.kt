package com.example.task.data

import com.example.task.model.NewsResponse
import com.example.task.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        countryCode: String,
        @Query("page")
        pageNumber: Int,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ): NewsResponse

}
