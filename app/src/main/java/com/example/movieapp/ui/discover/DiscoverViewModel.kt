package com.example.movieapp.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movieapp.data.DiscoverRepository
import com.example.movieapp.model.Discover
import com.example.movieapp.model.NetworkResponse
import com.example.movieapp.model.Result
import kotlinx.coroutines.Dispatchers

class DiscoverViewModel(private var discoverRepository: DiscoverRepository)  : ViewModel() {

    var popularMovies : LiveData<NetworkResponse<Discover>?> = liveData(Dispatchers.IO){
        emit(discoverRepository.getPopular())
    }

    var upcomingMovies : LiveData<NetworkResponse<Discover>?> = liveData(Dispatchers.IO){
        emit(discoverRepository.getUpcoming())
    }

    var nowPlayingMovies : LiveData<NetworkResponse<Discover>?> = liveData(Dispatchers.IO){
        emit(discoverRepository.getNowPlaying())
    }




}
