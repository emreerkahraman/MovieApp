package com.example.movieapp.data

import com.example.movieapp.model.Discover
import com.example.movieapp.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {

    @GET("movie/popular")
    suspend fun getPopular()  : Discover

    @GET("movie/popular")
    suspend fun getPopularPaged( @Query("page") page :Int)  : Response<Discover>

    @GET("movie/upcoming")
    suspend fun getUpcoming()  : Discover

    @GET("movie/upcoming")
    suspend fun getUpcomingPaged( @Query("page") page :Int)  : Response<Discover>

    @GET("movie/now_playing")
    suspend fun getNowPlaying()  : Discover

    @GET("movie/now_playing")
    suspend fun getNowPlayingPaged( @Query("page") page :Int)  : Response<Discover>

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: Int) : Movie

    @GET("search/movie")
    suspend fun searchMoviePaged(@Query("query")  movie: String, @Query("page") page :Int)  : Response<Discover>


}