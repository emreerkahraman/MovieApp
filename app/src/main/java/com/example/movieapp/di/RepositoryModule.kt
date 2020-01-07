package com.example.movieapp.di

import com.example.movieapp.data.DiscoverRepository
import com.example.movieapp.data.MovieDetailRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { DiscoverRepository(get()) }
    factory { MovieDetailRepository(get()) }

}