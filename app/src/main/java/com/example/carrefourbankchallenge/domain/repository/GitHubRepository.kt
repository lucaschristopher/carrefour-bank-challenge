package com.example.carrefourbankchallenge.domain.repository

import androidx.paging.PagingData
import com.example.carrefourbankchallenge.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {

    fun getUsers(): Flow<PagingData<UserModel>>

    suspend fun getUserDetails(username: String): Flow<UserModel>

    suspend fun searchUser(username: String): Flow<UserModel>
}