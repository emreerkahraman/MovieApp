package com.example.movieapp.data

import com.example.movieapp.model.Discover
import com.example.movieapp.model.NetworkResponse

class DiscoverRepository (var tmdbService: TmdbService) : BaseRepository() {


    suspend fun getPopular()  : NetworkResponse<Discover>? {
        return safeApiCall({tmdbService.getPopular()},"Popular Error")
    }

    suspend fun getUpcoming()  :  NetworkResponse<Discover>? {
        return safeApiCall({tmdbService.getUpcoming()},"Upcoming Error")
    }

    suspend fun getNowPlaying()  :  NetworkResponse<Discover>? {
        return safeApiCall({tmdbService.getNowPlaying()},"Now Playing Error")
    }


}