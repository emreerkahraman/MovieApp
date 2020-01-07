package com.example.movieapp.di

import com.example.movieapp.data.TmdbService
import com.example.movieapp.utils.ApiKeyInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { ApiKeyInterceptor() }
    factory { provideOkHttpClient(get()) }
    single { provideTmdpService(get()) }

}

fun provideTmdpService(okHttpClient: OkHttpClient): TmdbService {
    return Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build().create(TmdbService::class.java)
}

fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(apiKeyInterceptor).build()
}