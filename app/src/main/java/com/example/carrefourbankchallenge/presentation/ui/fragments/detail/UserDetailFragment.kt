package com.example.carrefourbankchallenge.presentation.ui.fragments.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.carrefourbankchallenge.R
import com.example.carrefourbankchallenge.presentation.model.Result
import com.example.carrefourbankchallenge.presentation.ui.components.AppTopBar
import com.example.carrefourbankchallenge.presentation.ui.components.ErrorDialog
import com.example.carrefourbankchallenge.presentation.ui.components.LoadingComponent
import com.example.carrefourbankchallenge.presentation.ui.theme.WhiteAccent
import com.example.carrefourbankchallenge.presentation.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserDetailFragment(
    modifier: Modifier = Modifier,
    actionNavigateBack: () -> Unit,
    username: String,
) {

    val viewModel = koinViewModel<UserViewModel>()

    fun getUserDetails() = viewModel.getUserDetails(username)

    getUserDetails()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = WhiteAccent),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.title_detail, username),
                showBackButton = true,
                navigateBack = actionNavigateBack,
            )
        }
    ) {
        it
        when (val response = viewModel.userDetails.collectAsStateWithLifecycle().value) {
            is Result.Loading -> LoadingComponent()
            is Result.Error -> ErrorDialog(onClick = { getUserDetails() })
            is Result.Success -> UserDetailScreen(user = response.data)
            else -> Unit
        }
    }
}