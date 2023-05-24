package com.example.carrefourbankchallenge.data.repository


import androidx.paging.PagingData
import androidx.paging.map
import com.example.carrefourbankchallenge.data.datasource.remote.GitHubRemoteDataSource
import com.example.carrefourbankchallenge.data.datasource.remote.model.toDomainModel
import com.example.carrefourbankchallenge.domain.model.UserModel
import com.example.carrefourbankchallenge.domain.repository.GitHubRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class GitHubRepositoryImpl(
    private val remoteDataSource: GitHubRemoteDataSource,
    private val dispatcher: CoroutineDispatcher
) : GitHubRepository {

    override fun getUsers(): Flow<PagingData<UserModel>> =
        this.remoteDataSource.getUsers().map { pagingData ->
            pagingData.map { it.toDomainModel() }
        }.flowOn(dispatcher)

    override suspend fun searchUser(username: String): Flow<UserModel> = flow {
        remoteDataSource.getUserDetails(username).collect { result ->
            emit(result.toDomainModel())
        }
    }.flowOn(dispatcher)

    override suspend fun getUserDetails(username: String): Flow<UserModel> = flow {
        remoteDataSource.getUserDetails(username).collect { result ->
            remoteDataSource.getUserRepositories(username).collect { repos ->
                result.repos = repos
            }
            emit(result.toDomainModel())
        }
    }.flowOn(dispatcher)
}