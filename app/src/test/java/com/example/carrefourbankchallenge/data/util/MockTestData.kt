package com.example.carrefourbankchallenge.data.util

import com.example.carrefourbankchallenge.core.constants.*
import com.example.carrefourbankchallenge.data.datasource.remote.model.RepositoryResponse
import com.example.carrefourbankchallenge.data.datasource.remote.model.UserResponse

val userResponseMock = UserResponse(
    id = ZERO,
    name = NO_NAME,
    email = NO_EMAIL,
    company = NO_COMPANY,
    bio = NO_BIO,
    avatarUrl = NO_AVATAR_URL,
    location = NO_LOCATION,
    login = NO_LOGIN,
    url = NO_URL
)

val repositoryResponseMock = RepositoryResponse(
    id = ZERO.toLong(),
    name = NO_NAME,
    description = NO_DESCRIPTION,
    url = NO_URL,
    forks = ZERO.toLong(),
    stargazers = ZERO.toLong(),
    owner = userResponseMock
)
