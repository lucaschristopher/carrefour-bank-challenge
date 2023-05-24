package com.example.carrefourbankchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.carrefourbankchallenge.domain.model.toUiModel
import com.example.carrefourbankchallenge.domain.repository.GitHubRepository
import com.example.carrefourbankchallenge.presentation.model.UserUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GitHubViewModel(
    private val repository: GitHubRepository
) : ViewModel() {

    fun getUsers(): Flow<PagingData<UserUiModel>> {
        return repository.getUsers().map { pagingData ->
            pagingData.map { it.toUiModel() }
        }.cachedIn(viewModelScope)
    }
}