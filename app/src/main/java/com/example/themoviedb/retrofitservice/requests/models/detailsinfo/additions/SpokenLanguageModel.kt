package com.example.themoviedb.retrofitservice.requests.models.detailsinfo.additions

import com.google.gson.annotations.SerializedName

class SpokenLanguageModel(
    @SerializedName("iso_639_1")
    val code: String,
    @SerializedName("name")
    val name: String
)