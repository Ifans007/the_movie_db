package com.example.themoviedb.retrofitservice.requests.models

import com.google.gson.annotations.SerializedName

class CommonInfoMoviesModel(

    @SerializedName("id")
    var movieId: Int,
    @SerializedName("adult")
    var adult: Boolean,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("genre_ids")
    var genreIds: List<Int>,
    @SerializedName("original_language")
    var originalLanguage: String,
    @SerializedName("original_title")
    var originalTitle: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("popularity")
    var popularity: Float,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("release_date")
    var releaseDate: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("video")
    var video: Boolean,
    @SerializedName("vote_average")
    var voteAverage: Float,
    @SerializedName("vote_count")
    var voteCount: Int
)
