package com.example.movieapp.ui.nowplaying_movies

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.movieapp.data.TmdbService
import com.example.movieapp.model.Result
import kotlinx.coroutines.CoroutineScope

class NowPlayingMoviesDataSourceFactory (private var service: TmdbService,
                                         private val scope: CoroutineScope
) : DataSource.Factory<Int, Result>() {

    private val liveDataSource = MutableLiveData<NowPlayingMoviesDataSource>()

    override fun create(): DataSource<Int, Result> {
        val source = NowPlayingMoviesDataSource(service, scope)
        liveDataSource.postValue(source)
        return  source
    }
}