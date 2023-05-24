package com.example.carrefourbankchallenge.domain.model

import com.example.carrefourbankchallenge.presentation.model.UserUiModel

class UserModel(
    val id: Int,
    val email: String,
    val login: String,
    val name: String,
    val avatarUrl: String,
    val url: String,
    val bio: String,
    val location: String,
    val company: String,
    val repos: List<RepositoryModel>
)

fun UserModel.toUiModel() = UserUiModel(
    id = this.id,
    email = this.email,
    login = this.login,
    name = this.name,
    avatarUrl = this.avatarUrl,
    location = this.location,
    company = this.company,
    repos = repos.map { it.toUiModel() }
)
