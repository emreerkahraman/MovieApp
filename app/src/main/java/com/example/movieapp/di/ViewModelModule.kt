package com.example.movieapp.di

import com.example.movieapp.ui.detail.MovieDetailViewModel
import com.example.movieapp.ui.discover.DiscoverViewModel
import com.example.movieapp.ui.nowplaying_movies.NowplayingMoviesViewModel
import com.example.movieapp.ui.popular_movies.PopularMoviesViewModel
import com.example.movieapp.ui.search_movies.SearchMovieViewModel
import com.example.movieapp.ui.upcoming_movies.UpcomingMoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {


    viewModel { MovieDetailViewModel(get()) }
    viewModel { DiscoverViewModel(get()) }
    viewModel { PopularMoviesViewModel(get()) }
    viewModel { UpcomingMoviesViewModel(get()) }
    viewModel { NowplayingMoviesViewModel(get()) }
    viewModel { SearchMovieViewModel(get()) }
}