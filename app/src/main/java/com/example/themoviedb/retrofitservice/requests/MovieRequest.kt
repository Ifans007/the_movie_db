package com.example.themoviedb.retrofitservice.requests

import com.example.themoviedb.retrofitservice.requests.models.Movie
import com.google.gson.annotations.SerializedName

class MovieRequest(
    @SerializedName("total_pages") var totalPages: Int = 0,
    @SerializedName("page") var page: Int = 0,
    @SerializedName("results") var results: List<Movie>? = null
)