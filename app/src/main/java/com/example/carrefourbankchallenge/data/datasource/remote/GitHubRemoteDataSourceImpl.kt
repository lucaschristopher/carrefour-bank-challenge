package com.example.carrefourbankchallenge.data.datasource.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.carrefourbankchallenge.core.constants.PAGE_SIZE
import com.example.carrefourbankchallenge.data.datasource.remote.model.RepositoryResponse
import com.example.carrefourbankchallenge.data.datasource.remote.model.UserResponse
import com.example.carrefourbankchallenge.data.datasource.remote.paging.GitHubPagingSource
import com.example.carrefourbankchallenge.data.service.GitHubService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GitHubRemoteDataSourceImpl(
    private val service: GitHubService
) : GitHubRemoteDataSource {

    override fun getUsers(): Flow<PagingData<UserResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GitHubPagingSource(service)
            }
        ).flow
    }

    // TODO: to implement mapToCustomError()
    override suspend fun getUserDetails(username: String): Flow<UserResponse> = flow {
        val response = service.getUserDetails(username)
        emit(response)
    }

    override suspend fun getUserRepositories(username: String): Flow<List<RepositoryResponse>> =
        flow {
            val response = service.getUserRepositories(username)
            emit(response)
        }
}