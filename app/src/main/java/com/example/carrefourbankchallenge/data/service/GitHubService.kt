package com.example.carrefourbankchallenge.data.service

import com.example.carrefourbankchallenge.data.datasource.remote.model.RepositoryResponse
import com.example.carrefourbankchallenge.data.datasource.remote.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET(USERS)
    suspend fun getUsers(
        @Query(PAGE) page: Int,
        @Query(PER_PAGE) pageSize: Int
    ): List<UserResponse>

    @GET(USERS + USERNAME)
    suspend fun getUserDetails(@Path(USERNAME_PATH) userId: String): UserResponse

    @GET(USERS + USERNAME + REPOS)
    suspend fun getUserRepositories(@Path(USERNAME_PATH) userId: String): List<RepositoryResponse>

    private companion object {
        const val USERS = "/users"
        const val USERNAME = "/{username}"
        const val USERNAME_PATH = "username"
        const val REPOS = "/repos"

        const val PAGE = "page"
        const val PER_PAGE = "per_page"
    }
}