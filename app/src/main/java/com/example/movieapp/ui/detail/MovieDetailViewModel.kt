package com.example.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movieapp.data.MovieDetailRepository
import com.example.movieapp.model.Movie
import kotlinx.coroutines.Dispatchers

class MovieDetailViewModel(private var movieDetailRepository: MovieDetailRepository) : ViewModel() {


    lateinit var movie :LiveData<Movie>

    fun getMovie( movieId :Int): LiveData<Movie> {

        movie =liveData(Dispatchers.IO) {

            emit(movieDetailRepository.getMovie(movieId))
        }

        return movie


    }


}