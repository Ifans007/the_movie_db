package com.example.themoviedb.retrofitservice.requests.models.detailsinfo.additions

import com.google.gson.annotations.SerializedName

class ProductionCountryModel(
    @SerializedName("iso_3166_1")
    val code: String,
    @SerializedName("name")
    val name: String
)