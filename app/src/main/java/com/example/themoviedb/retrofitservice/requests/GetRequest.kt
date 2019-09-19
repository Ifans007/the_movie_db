package com.example.themoviedb.retrofitservice.requests

import com.example.themoviedb.retrofitservice.RetrofitClient
import com.example.themoviedb.retrofitservice.requests.models.GenresRequest
import com.example.themoviedb.retrofitservice.requests.models.MoviesRequest
import com.example.themoviedb.retrofitservice.requests.models.detailsinfo.DetailsInfoMoviesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val service: Requests = RetrofitClient.instance
val TMDB_API_KEY = "6e28454b2acfbee3d6116e0792901bd7"

object GetRequest {

    fun getGenresList(onSuccess: (movie: GenresRequest) -> Unit,
                      onError: (error: String) -> Unit) {

        service.genresList(
            TMDB_API_KEY,
            "ru-RU")
            .enqueue(



                object : Callback<GenresRequest> {


                    override fun onFailure(call: Call<GenresRequest>, t: Throwable) {
                        onError(t.message ?: "unknown error")
                    }

                    override fun onResponse(call: Call<GenresRequest>, response: Response<GenresRequest>) {
                        if (response.isSuccessful) {
                            val genreModel = response.body() ?: GenresRequest()
                            onSuccess(genreModel)
                        } else {
                            onError(response.errorBody()?.string() ?: "Unknown error")
                        }
                    }
                }
            )
    }

    fun getDetailsMovieById(movieId: Int): DetailsInfoMoviesModel? {

        var response: DetailsInfoMoviesModel? = null

        try {

            response = service.detailsMovieById(
                movieId,
                TMDB_API_KEY,
                "ru-RU",
                "RU"
            ).execute().body()


        } catch (e: Exception) {
            e.printStackTrace()
        }

        return response

    }

    fun getPopularMovies(
        page: Int,
        onSuccess: (moviesRequest: MoviesRequest) -> Unit,
        onError: (error: String) -> Unit) {

        service.popularMovies(
            TMDB_API_KEY,
            "ru-RU",
            page,
            "RU")
            .enqueue(
                object : Callback<MoviesRequest> {
                    override fun onFailure(call: Call<MoviesRequest>, t: Throwable) {
                        onError(t.message ?: "unknown error")
                    }

                    override fun onResponse(call: Call<MoviesRequest>, response: Response<MoviesRequest>) {
                        if (response.isSuccessful) {
                            val movieRequest = response.body() ?: MoviesRequest()
                            onSuccess(movieRequest)
                        } else {
                            onError(response.errorBody()?.string() ?: "Unknown error")
                        }
                    }

                })
    }

}

