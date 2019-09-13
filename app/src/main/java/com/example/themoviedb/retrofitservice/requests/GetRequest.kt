package com.example.themoviedb.retrofitservice.requests

import com.example.themoviedb.retrofitservice.RetrofitClient
import com.example.themoviedb.retrofitservice.requests.models.MoviesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val service: Requests = RetrofitClient.instance
val TMDB_API_KEY = "6e28454b2acfbee3d6116e0792901bd7"

object GetRequest {

    fun getMovieById(movieId: Int,
                     onSuccess: (movie: MoviesModel) -> Unit,
                     onError: (error: String) -> Unit) {

        service.movieById(
            TMDB_API_KEY,
            "ru-RU",
            movieId,
            "RU")
            .enqueue(
                object : Callback<MoviesModel> {
                    override fun onFailure(call: Call<MoviesModel>, t: Throwable) {
                        onError(t.message ?: "unknown error")
                    }

                    override fun onResponse(call: Call<MoviesModel>, response: Response<MoviesModel>) {
                        if (response.isSuccessful) {
                            val movieRequest = response.body() ?: MoviesModel()
                            onSuccess(movieRequest)
                        } else {
                            onError(response.errorBody()?.string() ?: "Unknown error")
                        }
                    }

                })

    }

    fun getPopularMovies(
        page: Int,
        onSuccess: (movieRequest: MovieRequest) -> Unit,
        onError: (error: String) -> Unit) {

        service.popularMovies(
            TMDB_API_KEY,
            "ru-RU",
            page,
            "RU")
            .enqueue(
                object : Callback<MovieRequest> {
                    override fun onFailure(call: Call<MovieRequest>, t: Throwable) {
                        onError(t.message ?: "unknown error")
                    }

                    override fun onResponse(call: Call<MovieRequest>, response: Response<MovieRequest>) {
                        if (response.isSuccessful) {
                            val movieRequest = response.body() ?: MovieRequest()
                            onSuccess(movieRequest)
                        } else {
                            onError(response.errorBody()?.string() ?: "Unknown error")
                        }
                    }

                })
    }

}

