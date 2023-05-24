package com.example.carrefourbankchallenge.data.datasource.remote

import androidx.paging.PagingData
import com.example.carrefourbankchallenge.data.datasource.remote.model.RepositoryResponse
import com.example.carrefourbankchallenge.data.datasource.remote.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface GitHubRemoteDataSource {

    fun getUsers(): Flow<PagingData<UserResponse>>

    suspend fun getUserDetails(username: String): Flow<UserResponse>

    suspend fun getUserRepositories(username: String): Flow<List<RepositoryResponse>>
}