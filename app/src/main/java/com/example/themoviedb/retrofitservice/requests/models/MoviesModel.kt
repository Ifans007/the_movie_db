package com.example.themoviedb.retrofitservice.requests.models

import com.google.gson.annotations.SerializedName

class MoviesModel {

    @SerializedName("adult")
    val adult: Boolean? = null
    @SerializedName("backdrop_path")
    val backdropPath: String? = null
    @SerializedName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection? = null
    @SerializedName("budget")
    val budget: Int? = null
    @SerializedName("genres")
    val genresList: List<Genre>? = null
    @SerializedName("homepage")
    val homepage: String? = null
    @SerializedName("id")
    val id: Int? = null
    @SerializedName("imdb_id")
    val imdbId: String? = null
    @SerializedName("original_language")
    val originalLanguage: String? = null
    @SerializedName("original_title")
    val originalTitle: String? = null
    @SerializedName("overview")
    val overview: String? = null
    @SerializedName("popularity")
    val popularity: Double? = null
    @SerializedName("poster_path")
    val posterPath: String? = null
    @SerializedName("production_companies")
    val productionCompaniesList: List<ProductionCompany>? = null
    @SerializedName("production_countries")
    val productionCountriesList: List<ProductionCountry>? = null
    @SerializedName("release_date")
    val releaseDate: String? = null
    @SerializedName("revenue")
    val revenue: Long? = null
    @SerializedName("runtime")
    val runtime: Int? = null
    @SerializedName("spoken_languages")
    val spokenLanguagesList: List<SpokenLanguage>? = null
    @SerializedName("status")
    val status: String? = null
    @SerializedName("tagline")
    val tagline: String? = null
    @SerializedName("title")
    val title: String? = null
    @SerializedName("video")
    val video: Boolean? = null
    @SerializedName("vote_average")
    val voteAverage: Double? = null
    @SerializedName("vote_count")
    val voteCount: Int? = null
}

class BelongsToCollection(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String
)

class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

class ProductionCompany(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)

class ProductionCountry(
    @SerializedName("iso_3166_1")
    val code: String,
    @SerializedName("name")
    val name: String
)

class SpokenLanguage(
    @SerializedName("iso_639_1")
    val code: String,
    @SerializedName("name")
    val name: String
)