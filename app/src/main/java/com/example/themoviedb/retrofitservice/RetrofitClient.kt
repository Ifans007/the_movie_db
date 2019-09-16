package com.example.themoviedb.retrofitservice

import com.example.themoviedb.retrofitservice.requests.Requests
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val BASE_URL = "https://api.themoviedb.org/3/"

//    private val mRetrofit: Retrofit

    val instance: Requests by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(Requests::class.java)
    }
}