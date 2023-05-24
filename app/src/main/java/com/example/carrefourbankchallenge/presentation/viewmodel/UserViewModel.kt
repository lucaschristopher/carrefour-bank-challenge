package com.example.carrefourbankchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carrefourbankchallenge.domain.model.UserModel
import com.example.carrefourbankchallenge.domain.model.toUiModel
import com.example.carrefourbankchallenge.domain.usecase.GetGitHubUserDetailsUseCase
import com.example.carrefourbankchallenge.presentation.model.Result
import com.example.carrefourbankchallenge.presentation.model.UserUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class UserViewModel(
    private val useCase: GetGitHubUserDetailsUseCase
) : ViewModel() {

    private val _userDetails = MutableStateFlow<Result<UserUiModel>>(Result.Initial)
    val userDetails = _userDetails.asStateFlow()

    fun getUserDetails(username: String) {
        viewModelScope.launch {
            useCase.invoke(username)
                .onStart { handleLoading() }
                .catch { handleError(it) }
                .collect { handleSuccess(it) }
        }
    }

    private fun handleLoading() {
        _userDetails.value = Result.Loading
    }

    private fun handleError(throwable: Throwable) {
        _userDetails.value = Result.Error(throwable.message)
    }

    private fun handleSuccess(userModel: UserModel) {
        _userDetails.value = Result.Success(userModel.toUiModel())
    }
}