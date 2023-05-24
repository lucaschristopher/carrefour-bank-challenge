package com.example.carrefourbankchallenge.data.di

import com.example.carrefourbankchallenge.BuildConfig
import com.example.carrefourbankchallenge.core.constants.DEFAULT_DISPATCHER
import com.example.carrefourbankchallenge.core.constants.DEFAULT_SCOPE
import com.example.carrefourbankchallenge.core.constants.IO_DISPATCHER
import com.example.carrefourbankchallenge.core.constants.MAIN_DISPATCHER
import com.example.carrefourbankchallenge.data.datasource.remote.GitHubRemoteDataSource
import com.example.carrefourbankchallenge.data.datasource.remote.GitHubRemoteDataSourceImpl
import com.example.carrefourbankchallenge.data.provider.factory.ApiFactory
import com.example.carrefourbankchallenge.data.provider.factory.OkHttpClientFactory
import com.example.carrefourbankchallenge.data.provider.factory.RetrofitFactory
import com.example.carrefourbankchallenge.data.repository.GitHubRepositoryImpl
import com.example.carrefourbankchallenge.data.service.GitHubService
import com.example.carrefourbankchallenge.domain.repository.GitHubRepository
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    factory { ApiFactory.build(retrofit = get(), apiClass = GitHubService::class.java) }
}

val networkModule = module {
    factory {
        OkHttpClientFactory.build()
    }

    factory<Converter.Factory> {
        GsonConverterFactory.create(GsonBuilder().create())
    }

    factory {
        RetrofitFactory.build(url = BuildConfig.BASE_URL, client = get(), factory = get())
    }
}

val repositoryModule = module {
    factory<GitHubRepository> {
        GitHubRepositoryImpl(
            remoteDataSource = get(),
            dispatcher = get(named(IO_DISPATCHER))
        )
    }
}

val dataSourceModule = module {
    factory<GitHubRemoteDataSource> {
        GitHubRemoteDataSourceImpl(
            service = get()
        )
    }
}

val dispatcherModule = module {
    factory(named(DEFAULT_DISPATCHER)) { Dispatchers.Default }
    factory(named(IO_DISPATCHER)) { Dispatchers.IO }
    factory(named(MAIN_DISPATCHER)) { Dispatchers.Main }
    factory(named(DEFAULT_SCOPE)) { CoroutineScope(Dispatchers.Default) }
}

object DataModule {
    fun load() = loadKoinModules(
        networkModule + apiModule + repositoryModule + dataSourceModule + dispatcherModule
    )
}