package com.example.movieapp.ui.search_movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.toLiveData
import com.example.movieapp.data.TmdbService

class SearchMovieViewModel (private val service: TmdbService) :ViewModel(){

    private val movieName: MutableLiveData<String> = MutableLiveData()



    val pagedMovieList=movieName.switchMap {

        val dataSourceFactory =SearchMovieDataSourceFactory(it,service,viewModelScope)

        dataSourceFactory.toLiveData(10)

    }



    fun searchMovie(movie:String){
        movieName.value=movie
    }

}