package com.example.themoviedb.retrofitservice.requests.models.detailsinfo.additions

import com.google.gson.annotations.SerializedName

class GenreModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)