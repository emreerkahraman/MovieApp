package com.example.movieapp.ui.search_movies

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.movieapp.data.TmdbService
import com.example.movieapp.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SearchMovieDataSource (
                             private var query :String,
                             private var service: TmdbService,
                             private val scope: CoroutineScope
): PageKeyedDataSource<Int, Result>() {


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        scope.launch {

            try {
                val response=service.searchMoviePaged(query,1)
                if (response.isSuccessful){

                    val movieList =response.body()?.results

                    if (movieList!!.isNotEmpty()){

                        callback.onResult(movieList,null,2)
                    }
                }


            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }


        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        scope.launch {
            try {

                val response=service.searchMoviePaged(query,params.key)
                if (response.isSuccessful){
                    val movieList =response.body()?.results

                    if(movieList!!.isNotEmpty()) {
                        callback.onResult(movieList, params.key.inc())
                    }

                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }




        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        scope.launch {
            try {

                val response=service.searchMoviePaged(query,params.key)
                if (response.isSuccessful){
                    val movieList =response.body()?.results

                    if(movieList!!.isNotEmpty()) {
                        callback.onResult(movieList, params.key.dec())
                    }

                }

            }catch (exception : Exception){
                Log.e("PostsDataSource", "Failed to fetch data!")
            }




        }
    }
}