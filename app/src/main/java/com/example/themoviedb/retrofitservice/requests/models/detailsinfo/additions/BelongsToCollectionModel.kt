package com.example.themoviedb.retrofitservice.requests.models.detailsinfo.additions

import com.google.gson.annotations.SerializedName

class BelongsToCollectionModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?
)