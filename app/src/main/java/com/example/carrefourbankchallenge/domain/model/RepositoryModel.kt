package com.example.carrefourbankchallenge.domain.model

import com.example.carrefourbankchallenge.presentation.model.RepositoryUiModel

class RepositoryModel(
    val id: Long,
    val name: String,
    val description: String,
    val url: String,
    val forks: Long,
    val stargazers: Long
)

fun RepositoryModel.toUiModel() = RepositoryUiModel(
    id = this.id,
    name = this.name,
    description = this.description,
    url = this.url,
    forks = this.forks,
    stargazers = this.stargazers
)