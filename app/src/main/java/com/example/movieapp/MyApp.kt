package com.example.movieapp

import android.app.Application
import com.example.movieapp.di.networkModule
import com.example.movieapp.di.repositoryModule
import com.example.movieapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
            startKoin {
                androidContext(this@MyApp)
                androidLogger()
                modules(listOf(networkModule, repositoryModule, viewModelModule))
            }
    }
}