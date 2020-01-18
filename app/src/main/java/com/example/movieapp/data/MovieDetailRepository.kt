package com.example.movieapp.data

import com.example.movieapp.model.Movie
import com.example.movieapp.model.NetworkResponse

class MovieDetailRepository (private val tmdbService: TmdbService) :BaseRepository(){


    suspend fun getMovie(movieId :Int) : NetworkResponse<Movie> {
        return safeApiCall({tmdbService.getMovie(movieId)},"Error with Movie Detail ")
    }
}