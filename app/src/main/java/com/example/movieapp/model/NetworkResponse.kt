package com.example.movieapp.model

sealed class NetworkResponse<out T : Any>{
    data class Success<out T : Any>(val data : T) : NetworkResponse<T>()
    data class Error(val message: String)  : NetworkResponse<Nothing>()
}
