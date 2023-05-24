package com.example.carrefourbankchallenge.presentation.ui.fragments.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.carrefourbankchallenge.presentation.model.UserUiModel
import com.example.carrefourbankchallenge.presentation.ui.fragments.home.widgets.UserItem

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    data: List<UserUiModel>,
    action: (String) -> Unit
) {

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(data) { user ->
            UserItem(
                modifier = modifier,
                data = user,
                action = action
            )
        }
    }
}