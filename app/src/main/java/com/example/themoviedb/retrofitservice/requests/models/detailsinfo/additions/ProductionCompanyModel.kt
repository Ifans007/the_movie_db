package com.example.themoviedb.retrofitservice.requests.models.detailsinfo.additions

import com.google.gson.annotations.SerializedName

class ProductionCompanyModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)