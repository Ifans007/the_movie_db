package com.example.themoviedb.ui.activity.moviedescription.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.themoviedb.R
import com.example.themoviedb.Resources
import com.example.themoviedb.convert.CountyCodeToFlag
import com.example.themoviedb.convert.PutSpacesInMoney
import com.example.themoviedb.database.entities.detailsinfo.DetailsInfoMoviesTable

class DetailsMovieInfoFragment(private var movieLiveData: MutableLiveData<DetailsInfoMoviesTable>) : Fragment() {

    private lateinit var mainView: View
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var poster: ImageView

    private lateinit var popularity: TextView
    private lateinit var year: TextView
    private lateinit var productionCounty: TextView
    private lateinit var budget: TextView
    private lateinit var revenue: TextView
    private lateinit var runtime: TextView

    private lateinit var tagline: TextView
    private lateinit var genres: TextView
    private lateinit var overview: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainView = inflater.inflate(R.layout.fragment_movie_details_info, container, false)

        initView()

        movieLiveData.observe(this, Observer { fillView(it) })

        return mainView
    }

    private fun initView() {
        swipeRefreshLayout = mainView.findViewById(R.id.fragment_movie_details_swipe_refresh)

        poster = mainView.findViewById(R.id.fragment_movie_details_info_poster)

        popularity = mainView.findViewById(R.id.fragment_movie_details_info_popularity)
        year = mainView.findViewById(R.id.fragment_movie_details_info_year)
        productionCounty = mainView.findViewById(R.id.fragment_movie_details_info_production_country)
        budget = mainView.findViewById(R.id.fragment_movie_details_info_budget)
        revenue = mainView.findViewById(R.id.fragment_movie_details_info_revenue)
        runtime = mainView.findViewById(R.id.fragment_movie_details_info_runtime)

        tagline = mainView.findViewById(R.id.fragment_movie_details_info_tagline)
        genres = mainView.findViewById(R.id.fragment_movie_details_info_genres)
        overview = mainView.findViewById(R.id.fragment_movie_details_info_overview)

    }

    @SuppressLint("SetTextI18n")
    private fun fillView(movie: DetailsInfoMoviesTable) {

        Glide.with(this).load(Resources.buildBackdropImageUrl(movie.posterPath))
            .transition(DrawableTransitionOptions.withCrossFade()).into(poster)

        popularity.text       = "Рейтинг: ${movie.voteAverage}/10 (${movie.voteCount})"
        year.text             = "Релиз: ${movie.releaseDate}"
        productionCounty.text = "Страна:  ${CountyCodeToFlag.toFlagEmoji(movie.productionCountriesList)}"
        budget.text           = "Бюджет: $${PutSpacesInMoney.putSpaceInMoney(movie.budget.toString())}"
        revenue.text          = "Сборы: $${PutSpacesInMoney.putSpaceInMoney(movie.revenue.toString())}"
        runtime.text          = "Время: ${movie.runtime} мин."



        tagline.text          = "Слоган:\n${movie.tagline}\n\n"
        genres.text           = "Жанр:\n${movie.genresList}\n\n"
        overview.text         = "Описание:\n${movie.overview}\n"

    }




}
