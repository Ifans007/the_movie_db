package com.example.themoviedb

object Resources {

    fun buildImagePosterUrl(path: String): String {
        return "http://image.tmdb.org/t/p/w342" + path
    }

    fun buildBackdropImageUrl(path: String): String {
        return "http://image.tmdb.org/t/p/w780" + path
    }
}