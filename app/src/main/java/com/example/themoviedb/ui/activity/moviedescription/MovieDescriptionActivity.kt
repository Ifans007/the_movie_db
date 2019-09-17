package com.example.themoviedb.ui.activity.moviedescription

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.themoviedb.R
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.cache.detailsinfo.DetailsMoviesCache
import com.example.themoviedb.database.entities.detailsinfo.DetailsInfoMoviesTable
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieDescriptionActivity : AppCompatActivity() {

    private val detailsMoviesCache = DetailsMoviesCache
    private val databaseApp = DatabaseApp.getInstance()
    private var movie: DetailsInfoMoviesTable? = null

//    private lateinit var inflateActivity: InflateActivity

    private lateinit var moviePoster: ImageView
    private lateinit var toolbar: Toolbar
    private lateinit var title: TextView
    private lateinit var releaseDate: TextView
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout

    override fun onCreate(savedInstanceState: Bundle?) = runBlocking {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_description)

        val movieId = intent.getIntExtra("movieId", 0)

//        inflateActivity = InflateActivity(this)

        initView()
        settingCollapsingToolbarLayout()
        setUpTransparentStatusBar(window)

        launch {

            movie = async(Dispatchers.IO) { getMovie(movieId) }.await()

            if (movie == null) {
                insertMovieInDatabase(movieId)
            }
        }.join()

        fillView(movie)

    }

    private fun initView() {
        toolbar = findViewById(R.id.activity_detail_toolbar)
        moviePoster = findViewById(R.id.activity_detail_backdrop_image)
        title = findViewById(R.id.activity_detail_title)
        releaseDate = findViewById(R.id.activity_detail_release_date)
        collapsingToolbarLayout = findViewById(R.id.activity_detail_collapsing_layout)
    }

    private fun fillView(movie: DetailsInfoMoviesTable?) {

        if (movie == null) return

        toolbar.title = movie.originalTitle
        title.text = movie.title
        releaseDate.text = movie.releaseDate

        Glide.with(applicationContext).load(buildBackdropImageUrl(movie.backdropPath!!))
            .transition(DrawableTransitionOptions.withCrossFade()).into(moviePoster)

    }

    private fun settingCollapsingToolbarLayout() {
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE)
        collapsingToolbarLayout.setCollapsedTitleTextColor(resources.getColor(R.color.color_dark_gray))
    }

    private fun setUpTransparentStatusBar(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

    private fun getMovie(movieId: Int): DetailsInfoMoviesTable? {
        return databaseApp.detailsInfoMoviesDao().getById(movieId)
    }


    private fun insertMovieInDatabase(movieId: Int) {
        detailsMoviesCache.insert(movieId,
            { resultMovie -> movie = resultMovie },
            { Toast.makeText(applicationContext, "Load details of movie: $it", Toast.LENGTH_LONG).show()})
    }

    private fun buildImageUrl(path: String): String {
        return "http://image.tmdb.org/t/p/w342" + path
    }

    fun buildBackdropImageUrl(path: String): String {
        return "http://image.tmdb.org/t/p/w780" + path
    }


}