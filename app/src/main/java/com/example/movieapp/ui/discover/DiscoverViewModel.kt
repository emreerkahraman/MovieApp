package com.example.movieapp.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movieapp.data.DiscoverRepository
import com.example.movieapp.model.Result
import kotlinx.coroutines.Dispatchers

class DiscoverViewModel(private var discoverRepository: DiscoverRepository)  : ViewModel() {

    var popularMovies : LiveData<List<Result?>?> = liveData(Dispatchers.IO){
        emit(discoverRepository.getPopular().results)
    }

    var upcomingMovies : LiveData<List<Result?>?> = liveData(Dispatchers.IO){
        emit(discoverRepository.getUpcoming().results)
    }

    var nowPlayingMovies : LiveData<List<Result?>?> = liveData(Dispatchers.IO){
        emit(discoverRepository.getNowPlaying().results)
    }


}
