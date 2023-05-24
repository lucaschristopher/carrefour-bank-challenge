package com.example.carrefourbankchallenge.domain.di

import com.example.carrefourbankchallenge.domain.repository.GitHubRepository
import com.example.carrefourbankchallenge.domain.usecase.GetGitHubUserDetailsUseCase
import com.example.carrefourbankchallenge.domain.usecase.GetGitHubUsersUseCase
import com.example.carrefourbankchallenge.domain.usecase.SearchGitHubUserUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetGitHubUsersUseCase(
            function = get<GitHubRepository>()::getUsers
        )
    }

    factory {
        GetGitHubUserDetailsUseCase(
            function = { username ->
                get<GitHubRepository>().getUserDetails(username)
            }
        )
    }

    factory {
        SearchGitHubUserUseCase(
            function = { username ->
                get<GitHubRepository>().searchUser(username)
            }
        )
    }
}

object DomainModule {
    fun load() = loadKoinModules(domainModule)
}