package com.example.task.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dfl.newsapi.NewsApiRepository
import com.example.task.data.NewsRepository
import com.example.task.model.Article
import com.example.task.util.Constants
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _newsHeadlines = MutableLiveData<State<List<Article>>>()
    val newsHeadlines: LiveData<State<List<Article>>> = _newsHeadlines

    private val _searchResults = MutableLiveData<State<List<Article>>>()
    val searchResults: LiveData<State<List<Article>>> = _searchResults

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        _searchResults.value = State.Loading
        _searchResults.value = try {
            val results = newsRepository.searchForNews(searchQuery, 1)
            results?.let { State.Success(it.articles) }
        } catch (e: Exception) {
            State.Error(e)
        }
    }

    fun fetchTopHeadlines(countryCode: String, pageNumber: Int) = viewModelScope.launch {
        _newsHeadlines.value = State.Loading
        _newsHeadlines.value = try {
            val headlines = newsRepository.getTopHeadlines(countryCode, pageNumber)
            headlines?.let { State.Success(it.articles) }
        } catch (e: Exception) {
            State.Error(e)
        }
    }

    sealed class State<out R> {
        object Loading : State<Nothing>()
        object Empty: State<Nothing>()
        data class Success<out T>(val data: List<Article>) : State<T>()
        data class Error(val exception: Throwable) : State<Nothing>()
    }
}
