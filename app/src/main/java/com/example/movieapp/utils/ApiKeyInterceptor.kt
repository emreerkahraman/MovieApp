package com.example.movieapp.utils

import android.util.Log
import com.example.movieapp.data.Credit
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor{


    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val url = request.url().newBuilder().addQueryParameter("api_key",Credit.APIKEY).build()

        request = request.newBuilder().url(url).build()

        Log.i("reqUrl",request.toString())

        return chain.proceed(request)

    }
}