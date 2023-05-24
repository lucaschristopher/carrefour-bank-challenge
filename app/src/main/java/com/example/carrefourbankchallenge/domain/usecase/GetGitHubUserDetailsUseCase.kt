package com.example.carrefourbankchallenge.domain.usecase

import com.example.carrefourbankchallenge.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

fun interface GetGitHubUserDetailsUseCase {
    suspend operator fun invoke(username: String): Flow<UserModel>
}