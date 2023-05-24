package com.example.carrefourbankchallenge

import android.app.Application
import com.example.carrefourbankchallenge.data.di.DataModule
import com.example.carrefourbankchallenge.domain.di.DomainModule
import com.example.carrefourbankchallenge.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun loadModules() {
        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidLogger()
            androidContext(this@App)
            loadModules()
        }
    }
}