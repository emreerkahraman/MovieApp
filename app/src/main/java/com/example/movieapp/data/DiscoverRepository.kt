package com.example.movieapp.data

import com.example.movieapp.model.Discover
import com.example.movieapp.model.NetworkResponse

class DiscoverRepository (var tmdbService: TmdbService) : BaseRepository() {


    suspend fun getPopular()  : NetworkResponse<Discover>? {
        return safeApiCall({tmdbService.getPopular()},"Error with Popular Movies")
    }

    suspend fun getUpcoming()  :  NetworkResponse<Discover>? {
        return safeApiCall({tmdbService.getUpcoming()},"Error with Upcoming Movies")
    }

    suspend fun getNowPlaying()  :  NetworkResponse<Discover>? {
        return safeApiCall({tmdbService.getNowPlaying()},"Error with Now Playing Movies")
    }


}