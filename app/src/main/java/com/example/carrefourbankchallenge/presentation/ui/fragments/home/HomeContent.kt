package com.example.carrefourbankchallenge.presentation.ui.fragments.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.carrefourbankchallenge.presentation.model.UserUiModel
import com.example.carrefourbankchallenge.presentation.ui.components.ErrorDialog
import com.example.carrefourbankchallenge.presentation.ui.components.LoadingComponent
import com.example.carrefourbankchallenge.presentation.ui.fragments.home.widgets.UserItem

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    pagingData: LazyPagingItems<UserUiModel>,
    action: (String) -> Unit
) {

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(
            count = pagingData.itemCount,
            key = pagingData.itemKey(),
            contentType = pagingData.itemContentType()
        ) { index ->
            val item = pagingData[index]
            item?.let {
                UserItem(
                    modifier = modifier,
                    data = item,
                    action = action
                )
            }
        }
    }

    pagingData.apply {
        when (loadState.refresh) {
            is LoadState.Loading -> LoadingComponent()
            is LoadState.Error -> {
                ErrorDialog(
                    modifier = modifier,
                    errorMessage = (loadState.refresh as LoadState.Error).error.message,
                    onClick = { retry() }
                )
            }
            else -> {}
        }
    }
}