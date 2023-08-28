package com.example.task.data

import com.example.task.model.NewsResponse
import com.example.task.util.NetworkInstance.api

class NewsRepository {
    suspend fun getTopHeadlines(countryCode: String, pageNumber: Int): NewsResponse {
        return api.getTopHeadlines(countryCode, pageNumber)
    }

    suspend fun searchForNews(searchQuery: String, pageNumber: Int): NewsResponse {
        return api.searchForNews(searchQuery, pageNumber)
    }
}
