package com.example.carrefourbankchallenge.presentation.model

data class RepositoryUiModel(
    val id: Long,
    val name: String,
    val description: String,
    val url: String,
    val forks: Long,
    val stargazers: Long
)