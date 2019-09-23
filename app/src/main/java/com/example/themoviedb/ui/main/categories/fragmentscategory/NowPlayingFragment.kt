package com.example.themoviedb.ui.main.categories.fragmentscategory

import com.example.themoviedb.retrofitservice.requests.GetRequest
import com.example.themoviedb.retrofitservice.requests.MovieCategory
import com.example.themoviedb.retrofitservice.requests.models.MoviesRequest
import com.example.themoviedb.ui.main.categories.AbstractFragmentCategory

class NowPlayingFragment : AbstractFragmentCategory() {

    override fun getRequest(
        page: Int,
        onSuccess: (moviesRequest: MoviesRequest) -> Unit,
        onError: (error: String) -> Unit
    ) {

        GetRequest.getMoviesCategory(MovieCategory.NOW_PLAYING, page, onSuccess, onError)
    }

}