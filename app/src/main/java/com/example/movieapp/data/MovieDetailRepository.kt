package com.example.movieapp.data

import com.example.movieapp.model.Movie

class MovieDetailRepository (private val tmdbService: TmdbService){


    suspend fun getMovie(movieId :Int) : Movie {
        return  tmdbService.getMovie(movieId)
    }
}