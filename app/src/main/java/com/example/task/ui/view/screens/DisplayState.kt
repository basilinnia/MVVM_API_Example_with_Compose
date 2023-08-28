package com.example.task.ui.view.screens

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.task.model.Article
import com.example.task.viewmodel.NewsViewModel

@Composable
fun DisplayState(state: NewsViewModel.State<List<Article>>,) {
    when (state) {
        is NewsViewModel.State.Loading -> {
            CircularProgressIndicator()
        }
        is NewsViewModel.State.Success -> {
            LazyColumn {
                items(state.data) { article ->
                    Text(article.title)
                }
            }
        }
        is NewsViewModel.State.Error -> {
            // Replace this with your error UI
            Text("An error occurred: ${state.exception}")
        }
        NewsViewModel.State.Empty -> {
            // Handle the empty state if needed
            Text("Let's do something")
        }
    }
}



