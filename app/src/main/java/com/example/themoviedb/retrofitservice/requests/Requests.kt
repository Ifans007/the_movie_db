package com.example.themoviedb.retrofitservice.requests

import com.example.themoviedb.retrofitservice.requests.models.MoviesModel
import com.example.themoviedb.retrofitservice.requests.models.PopularMoviesModel
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Query

interface Requests {

    @GET("movie/{movie_id}")
    fun movieById(@Query("api_key") apiKey: String,
                  @Query("language") language: String,
                  @Part("movie_id") movieId: Int,
                  @Query("region") region: String
    ): Call<MoviesModel>


    @GET("movie/popular")
    fun popularMovies(@Query("api_key") apiKey: String,
                         @Query("language") language: String,
                         @Query("page") pageNumber: Int,
                         @Query("region") region: String
    ): Call<MovieRequest>

}

class MovieRequest(
    @SerializedName("total_pages") var totalPages: Int = 0,
    @SerializedName("page") var page: Int = 0,
    @SerializedName("results") var results: List<PopularMoviesModel>? = null
)

