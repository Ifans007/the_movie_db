package com.example.themoviedb.ui.activity.moviedescription.inflaters

import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.themoviedb.R
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.cache.detailsinfo.DetailsMoviesCache
import com.example.themoviedb.database.entities.detailsinfo.DetailsInfoMoviesTable
import com.example.themoviedb.ui.activity.moviedescription.MovieDescriptionActivity
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class InflateActivity(private val movieDescr: MovieDescriptionActivity) {

    private val detailsMoviesCache = DetailsMoviesCache
    private val databaseApp = DatabaseApp.getInstance()
    private var movie: DetailsInfoMoviesTable? = null

    private lateinit var moviePoster: ImageView
    private lateinit var toolbar: Toolbar
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout

    init {
        toolbar = movieDescr.findViewById(R.id.activity_detail_toolbar)
        moviePoster = movieDescr.findViewById(R.id.activity_detail_backdrop_image)
        collapsingToolbarLayout = movieDescr.findViewById(R.id.activity_detail_collapsing_layout)


    }

    fun go(movieId: Int) {

        runBlocking {

            launch(Dispatchers.IO) {

//                val movieId = intent.getIntExtra("movieId", 0)
//
//                launch {
//                    initView()
//                    settingCollapsingToolbarLayout()
//                    setUpTransparentStatusBar(window)
//                }
//
                launch {
                    movie = async { getMovie(movieId) }.await()
//
                    if (movie == null) {
                        insertMovieInDatabase(movieId)
                    }
                }
//
            }.join()

            launch { fillView(movie) }

        }


    }

    private fun getMovie(movieId: Int): DetailsInfoMoviesTable? {
        return databaseApp.detailsInfoMoviesDao().getById(movieId)
    }

    private fun insertMovieInDatabase(movieId: Int) {
//        detailsMoviesCache.insert(movieId,
//            { resultMovie -> movie = resultMovie },
//            { Toast.makeText(movieDescr, "Load details of movie $it", Toast.LENGTH_SHORT).show()})
    }

    private fun fillView(movie: DetailsInfoMoviesTable?) {

        if (movie == null) return

        toolbar.title = movie.title

        Glide.with(movieDescr).load(buildBackdropImageUrl(movie.backdropPath!!))
            .transition(DrawableTransitionOptions.withCrossFade()).into(moviePoster)

    }

    fun buildBackdropImageUrl(path: String): String {
        return "http://image.tmdb.org/t/p/w780" + path
    }


}