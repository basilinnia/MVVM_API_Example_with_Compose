package com.example.task.ui.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.task.model.Article
import com.example.task.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp(viewModel: NewsViewModel) {
    var userInput by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }
    val newsHeadlinesState: NewsViewModel.State<List<Article>> by viewModel.newsHeadlines.observeAsState(
        NewsViewModel.State.Empty
    )
    val searchResultsState: NewsViewModel.State<List<Article>> by viewModel.searchResults.observeAsState(
        NewsViewModel.State.Empty
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = userInput,
            onValueChange = { userInput = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textStyle = MaterialTheme.typography.bodyLarge,
            placeholder = { if (isSearching) Text("Get everything about x ")  else Text("Return only top headlines")}
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Search Mode:")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = isSearching,
                onCheckedChange = { isSearching = it },
                modifier = Modifier.padding(start = 8.dp)
            )
        }

            Button(
                onClick = {
                    if (isSearching) {
                        viewModel.searchNews(userInput)
                    } else {
                        viewModel.fetchTopHeadlines("us", 1)
                    }
                },
                enabled = userInput.isNotEmpty()
            ) {
                Text("Fetch News")
            }

            when {
                isSearching -> DisplayState(searchResultsState)
                else -> DisplayState(newsHeadlinesState)
            }
        }
    }



