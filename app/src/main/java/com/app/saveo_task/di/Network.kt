package com.app.saveo_task.di

import com.app.saveo_task.remote.interfaces.APICalling
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    //url= "https://api.themoviedb.org/3/movie/popular"
    val BASE_URL="https://api.themoviedb.org/"
    val API_KEY="160d0d0be3e56d1d4f30ebac5fb17767"
    fun getRetrofit()= Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
    fun getApiService()= getRetrofit().create(APICalling::class.java)

}