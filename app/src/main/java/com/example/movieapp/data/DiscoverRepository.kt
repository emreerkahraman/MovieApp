package com.example.movieapp.data

import com.example.movieapp.model.Discover

class DiscoverRepository (var tmdbService: TmdbService){


    suspend fun getPopular()  : Discover {
        return tmdbService.getPopular()
    }

    suspend fun getUpcoming()  : Discover {
        return tmdbService.getUpcoming()
    }

    suspend fun getNowPlaying()  : Discover {
        return tmdbService.getNowPlaying()
    }
}