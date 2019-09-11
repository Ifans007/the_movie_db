package com.example.themoviedb.retrofitservice.requests.models

import com.example.themoviedb.retrofitservice.RetrofitClient
import com.example.themoviedb.retrofitservice.requests.MovieRequest
import com.example.themoviedb.retrofitservice.requests.Requests
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val service: Requests = RetrofitClient.instance
val TMDB_API_KEY = "6e28454b2acfbee3d6116e0792901bd7"

object GetRequest {

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

