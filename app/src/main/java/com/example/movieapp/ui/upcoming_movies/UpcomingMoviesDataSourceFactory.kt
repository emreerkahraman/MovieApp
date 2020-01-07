package com.example.movieapp.ui.upcoming_movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.movieapp.data.TmdbService
import com.example.movieapp.model.Result
import kotlinx.coroutines.CoroutineScope

class UpcomingMoviesDataSourceFactory(private var service: TmdbService,
                                      private val scope: CoroutineScope
) : DataSource.Factory<Int, Result>() {

    private val liveDataSource = MutableLiveData<UpcomingMoviesDataSource>()

    override fun create(): DataSource<Int, Result> {
        val source = UpcomingMoviesDataSource(service, scope)
        liveDataSource.postValue(source)
        return  source
    }
}