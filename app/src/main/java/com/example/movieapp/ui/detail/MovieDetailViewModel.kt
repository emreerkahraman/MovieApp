package com.example.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movieapp.data.MovieDetailRepository
import com.example.movieapp.model.Movie
import com.example.movieapp.model.NetworkResponse
import kotlinx.coroutines.Dispatchers

class MovieDetailViewModel(private var movieDetailRepository: MovieDetailRepository) : ViewModel() {


    lateinit var movie :LiveData<NetworkResponse<Movie>>

    fun getMovie( movieId :Int): LiveData<NetworkResponse<Movie>> {

        movie =liveData(Dispatchers.IO) {

            emit(movieDetailRepository.getMovie(movieId))
        }

        return movie


    }


}