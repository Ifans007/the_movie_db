package com.example.themoviedb.retrofitservice.requests.models

import com.example.themoviedb.retrofitservice.requests.models.detailsinfo.additions.GenreModel
import com.google.gson.annotations.SerializedName

class GenresRequest(
    @SerializedName("genres") val genresList: List<GenreModel>? = null
)