package com.example.carrefourbankchallenge.presentation.ui.fragments.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.carrefourbankchallenge.R
import com.example.carrefourbankchallenge.presentation.ui.components.AppTopBar
import com.example.carrefourbankchallenge.presentation.ui.components.ErrorDialog
import com.example.carrefourbankchallenge.presentation.ui.components.LoadingComponent
import com.example.carrefourbankchallenge.presentation.ui.theme.DarkBlue
import com.example.carrefourbankchallenge.presentation.ui.theme.WhiteAccent
import com.example.carrefourbankchallenge.presentation.viewmodel.GitHubViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeFragment(
    modifier: Modifier = Modifier,
    actionSearchUser: () -> Unit,
    actionOpenUserDetails: (String) -> Unit,
) {

    val viewModel = koinViewModel<GitHubViewModel>()
    val lazyPagingItems = viewModel.getUsers().collectAsLazyPagingItems()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = WhiteAccent),
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.title_home),
                showBackButton = false,
                navigateBack = { }
            )
        },
        content = {
            lazyPagingItems.apply {
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

            HomeContent(
                modifier = modifier.padding(it),
                data = lazyPagingItems.itemSnapshotList.items,
                action = actionOpenUserDetails
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = modifier,
                backgroundColor = DarkBlue,
                onClick = { actionSearchUser.invoke() }
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(id = R.string.description_search_button),
                    tint = Color.White
                )
            }
        }
    )
}