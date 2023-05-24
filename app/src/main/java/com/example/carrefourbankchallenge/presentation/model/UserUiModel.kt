package com.example.carrefourbankchallenge.presentation.model

data class UserUiModel(
    val id: Int,
    val email: String,
    val login: String,
    val name: String,
    val avatarUrl: String,
    val location: String,
    val company: String,
    val repos: List<RepositoryUiModel>
)