package com.example.carrefourbankchallenge.presentation.ui.fragments.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.carrefourbankchallenge.R
import com.example.carrefourbankchallenge.presentation.model.Result
import com.example.carrefourbankchallenge.presentation.model.UserUiModel
import com.example.carrefourbankchallenge.presentation.ui.components.LoadingComponent
import com.example.carrefourbankchallenge.presentation.ui.fragments.home.widgets.UserItem
import com.example.carrefourbankchallenge.presentation.ui.theme.dp4

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    state: Result<UserUiModel>,
    action: (String) -> Unit
) {
    when (state) {
        is Result.Loading -> {
            LoadingComponent()
        }
        is Result.Error -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = dp4)
            ) {
                Text(stringResource(id = R.string.error_user_not_found))
            }
        }
        is Result.Success -> {
            UserItem(
                modifier = modifier.padding(top = dp4),
                data = state.data,
                action = action
            )
        }
        else -> Unit
    }
}