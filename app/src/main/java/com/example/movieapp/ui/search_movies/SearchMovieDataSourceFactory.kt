package com.example.movieapp.ui.search_movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.movieapp.data.TmdbService
import com.example.movieapp.model.Result
import kotlinx.coroutines.CoroutineScope

class SearchMovieDataSourceFactory( private var query :String,
                                    private var service: TmdbService,
                                    private val scope: CoroutineScope
): DataSource.Factory<Int,Result>() {

    private val liveDataSource = MutableLiveData<SearchMovieDataSource>()
    override fun create(): DataSource<Int, Result> {
        val source = SearchMovieDataSource(query, service, scope)
        liveDataSource.postValue(source)
        return  source
    }


}