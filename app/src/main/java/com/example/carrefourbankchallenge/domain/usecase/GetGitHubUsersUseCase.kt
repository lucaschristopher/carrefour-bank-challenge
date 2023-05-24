package com.example.carrefourbankchallenge.domain.usecase

import androidx.paging.PagingData
import com.example.carrefourbankchallenge.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

fun interface GetGitHubUsersUseCase {
    operator fun invoke(): Flow<PagingData<UserModel>>
}
