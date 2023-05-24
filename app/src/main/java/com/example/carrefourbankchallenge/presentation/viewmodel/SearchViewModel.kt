package com.example.carrefourbankchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrefourbankchallenge.domain.model.UserModel
import com.example.carrefourbankchallenge.domain.model.toUiModel
import com.example.carrefourbankchallenge.domain.repository.GitHubRepository
import com.example.carrefourbankchallenge.presentation.model.Result
import com.example.carrefourbankchallenge.presentation.model.UserUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _searchStateFlow = MutableStateFlow<Result<UserUiModel>>(Result.Initial)
    val searchStateFlow = _searchStateFlow.asStateFlow()

    fun searchUser(queryString: String) {
        viewModelScope.launch {
            repository.searchUser(queryString)
                .onStart { handleLoading() }
                .catch { handleError(it) }
                .collect { handleSuccess(it) }
        }
    }

    private fun handleLoading() {
        _searchStateFlow.value = Result.Loading
    }

    private fun handleError(throwable: Throwable) {
        _searchStateFlow.value = Result.Error(throwable.message)
    }

    private fun handleSuccess(userModel: UserModel) {
        _searchStateFlow.value = Result.Success(userModel.toUiModel())
    }
}