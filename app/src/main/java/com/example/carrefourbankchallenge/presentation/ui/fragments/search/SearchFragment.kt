package com.example.carrefourbankchallenge.presentation.ui.fragments.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.carrefourbankchallenge.R
import com.example.carrefourbankchallenge.core.constants.EMPTY_STRING
import com.example.carrefourbankchallenge.presentation.model.Result
import com.example.carrefourbankchallenge.presentation.ui.components.AppTopBar
import com.example.carrefourbankchallenge.presentation.ui.components.LoadingComponent
import com.example.carrefourbankchallenge.presentation.ui.fragments.home.widgets.UserItem
import com.example.carrefourbankchallenge.presentation.ui.theme.WhiteAccent
import com.example.carrefourbankchallenge.presentation.ui.theme.dp16
import com.example.carrefourbankchallenge.presentation.ui.theme.dp4
import com.example.carrefourbankchallenge.presentation.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchFragment(
    modifier: Modifier = Modifier,
    actionNavigateBack: () -> Unit,
    actionOpenUserDetails: (String) -> Unit
) {

    val viewModel = koinViewModel<SearchViewModel>()

    val focusRequester = remember { FocusRequester() }
    val textState = remember { mutableStateOf(EMPTY_STRING) }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(color = WhiteAccent),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.title_search),
                showBackButton = true,
                navigateBack = actionNavigateBack,
            )
        },
        content = {
            it
            Column(
                modifier = modifier
                    .padding(dp16)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    modifier = modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    value = textState.value,
                    singleLine = true,
                    onValueChange = { textState.value = it },
                    label = { Text(stringResource(id = R.string.label_search_field)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(id = R.string.description_search_button)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search,
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            viewModel.searchUser(textState.value)
                            textState.value = EMPTY_STRING
                            focusManager.clearFocus()
                        }
                    ),
                )

                when (val response =
                    viewModel.searchStateFlow.collectAsStateWithLifecycle().value) {
                    is Result.Loading -> LoadingComponent()
                    is Result.Error -> {
                        Box(modifier = modifier
                            .fillMaxSize()
                            .padding(top = dp4)
                        ) {
                            Text(stringResource(id = R.string.error_user_not_found))
                        }
                    }
                    is Result.Success -> {
                        UserItem(
                            modifier = modifier.padding(top = dp4),
                            data = response.data,
                            action = actionOpenUserDetails
                        )
                    }
                    else -> Unit
                }
            }
        }
    )
}