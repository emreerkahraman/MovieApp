package com.example.movieapp.ui.upcoming_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.toLiveData
import com.example.movieapp.data.TmdbService
import com.example.movieapp.ui.popular_movies.PopularMoviesDataSourceFactory

class UpcomingMoviesViewModel(service: TmdbService) : ViewModel() {
    private var dataSourceFactory = PopularMoviesDataSourceFactory(service,viewModelScope)

    var pagedMovieList= dataSourceFactory.toLiveData(10)


}