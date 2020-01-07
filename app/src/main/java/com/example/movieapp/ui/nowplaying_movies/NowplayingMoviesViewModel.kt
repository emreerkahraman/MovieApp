package com.example.movieapp.ui.nowplaying_movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.toLiveData
import com.example.movieapp.data.TmdbService

class NowplayingMoviesViewModel (service: TmdbService) : ViewModel() {
    private var dataSourceFactory = NowPlayingMoviesDataSourceFactory(service,viewModelScope)

    var pagedMovieList= dataSourceFactory.toLiveData(10)


}