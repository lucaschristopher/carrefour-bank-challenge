package com.example.carrefourbankchallenge.presentation.di

import com.example.carrefourbankchallenge.presentation.viewmodel.GitHubViewModel
import com.example.carrefourbankchallenge.presentation.viewmodel.SearchViewModel
import com.example.carrefourbankchallenge.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        GitHubViewModel(repository = get())
    }

    viewModel {
        UserViewModel(repository = get())
    }

    viewModel {
        SearchViewModel(repository = get())
    }
}

object PresentationModule {
    fun load() = loadKoinModules(viewModelModule)
}