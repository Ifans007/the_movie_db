package com.example.themoviedb.retrofitservice.requests.models

import com.google.gson.annotations.SerializedName

class MoviesRequest(
    @SerializedName("total_pages") var totalPages: Int = 0,
    @SerializedName("page") var page: Int = 0,
    @SerializedName("results") var results: List<CommonInfoMoviesModel>? = null
)