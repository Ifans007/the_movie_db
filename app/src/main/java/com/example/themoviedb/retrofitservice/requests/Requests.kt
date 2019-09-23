package com.example.themoviedb.retrofitservice.requests

import com.example.themoviedb.retrofitservice.requests.models.GenresRequest
import com.example.themoviedb.retrofitservice.requests.models.MoviesRequest
import com.example.themoviedb.retrofitservice.requests.models.detailsinfo.DetailsInfoMoviesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Requests {

    @GET("movie/{movie_id}")
     fun detailsMovieById(@Path("movie_id") movieId: Int,
                         @Query("api_key") apiKey: String,
                         @Query("language") language: String,
                         @Query("region") region: String
    ): Call<DetailsInfoMoviesModel>


    @GET("movie/{movie_category}")
    fun movieCategory(@Path("movie_category") movieCategory: String,
                      @Query("api_key") apiKey: String,
                      @Query("language") language: String,
                      @Query("page") pageNumber: Int,
                      @Query("region") region: String
    ): Call<MoviesRequest>


    @GET("genre/movie/list")
    fun genresList(@Query("api_key") apiKey: String,
                   @Query("language") language: String
    ): Call<GenresRequest>

}

