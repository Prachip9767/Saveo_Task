package com.app.saveo_task.remote.interfaces

import com.app.saveo_task.remote.response.ResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface APICalling {
    @GET("3/movie/popular")
    suspend fun getInstance(@Query("api_key") key: String,
                            @Query("page")page:Int): ResponseDTO
}