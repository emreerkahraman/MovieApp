package com.example.movieapp.data

import android.util.Log
import com.example.movieapp.model.NetworkResponse
import retrofit2.Response
import java.io.IOException

open class BaseRepository {


    suspend fun<T : Any> safeApiCall(call: suspend()-> Response<T> , error: String) : NetworkResponse<T>{

        val response = call.invoke()

        return if (response.isSuccessful)

            NetworkResponse.Success(response.body()!!)

        else
            NetworkResponse.Error(error+" : " +response.message())
    }
}