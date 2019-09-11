package com.example.themoviedb.retrofitservice.requests

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Requests {
    @GET("movie/popular")
    fun popularMovies(@Query("api_key") apiKey: String,
                         @Query("language") language: String,
                         @Query("page") pageNumber: Int,
                         @Query("region") region: String
    ): Call<MovieRequest>
}