package com.example.themoviedb.ui.activity.moviedescription

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.MutableLiveData
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.themoviedb.R
import com.example.themoviedb.Resources
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.cache.detailsinfo.DetailsMoviesCache
import com.example.themoviedb.database.entities.detailsinfo.DetailsInfoMoviesTable
import com.example.themoviedb.ui.activity.moviedescription.fragments.DetailsMovieCastFragment
import com.example.themoviedb.ui.activity.moviedescription.fragments.DetailsMovieInfoFragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieDetailsActivity : AppCompatActivity() {

    private val detailsMoviesCache = DetailsMoviesCache
    private val databaseApp = DatabaseApp.getInstance()
    private var movie: DetailsInfoMoviesTable? = null
    private var movieLiveData = MutableLiveData<DetailsInfoMoviesTable>()

    private lateinit var moviePoster: ImageView
    private lateinit var toolbar: Toolbar
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
//    private lateinit var title: TextView
//    private lateinit var releaseDate: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        initView()

//        setSupportActionBar(toolbar)

        val movieId = intent.getIntExtra("movieId", 0)
        loadMovieInDatabase(movieId)

        settingCollapsingToolbarLayout()
        setUpTransparentStatusBar(window)

        setupViewPager()

        setupTabLayout()



    }

    private fun initView() {
        moviePoster = findViewById(R.id.activity_movie_details_backdrop_image)
        toolbar = findViewById(R.id.activity_movie_details_toolbar)
        collapsingToolbarLayout = findViewById(R.id.activity_movie_details_collapsing_layout)
        tabLayout = findViewById(R.id.activity_movie_details_tab_layout)
        viewPager = findViewById(R.id.activity_movie_details_view_pager)



//        title = findViewById(R.id.activity_detail_title)
//        releaseDate = findViewById(R.id.activity_detail_release_date)

    }

    private fun loadMovieInDatabase(movieId: Int) {


        runBlocking {

            launch {

                movie = async(Dispatchers.IO) { getMovie(movieId) }.await()

                if (movie == null) {
                    insertMovieInDatabase(movieId)
                }

                fillView(movie)
            }

        }

    }

    private fun getMovie(movieId: Int): DetailsInfoMoviesTable? {
        return databaseApp.detailsInfoMoviesDao().getById(movieId)
    }


    private fun insertMovieInDatabase(movieId: Int) {
        detailsMoviesCache.insert(
            movieId,

            { resultMovie ->
                movie = resultMovie
                fillView(movie)
            },

            { Toast.makeText(applicationContext, "Load details of movie: $it", Toast.LENGTH_LONG).show()})
    }

    private fun fillView(movie: DetailsInfoMoviesTable?) {

        if (movie == null) return

        toolbar.title = movie.title
//        title.text = movie.title
//        releaseDate.text = movie.releaseDate

        Glide.with(applicationContext).load(Resources.buildBackdropImageUrl(movie.backdropPath))
            .transition(DrawableTransitionOptions.withCrossFade()).into(moviePoster)

        movieLiveData.postValue(movie)

    }



    private fun setupViewPager() {
        val detailsMovieViewPagerAdapter =
            DetailsMovieViewPagerAdapter(
                supportFragmentManager
            )

        detailsMovieViewPagerAdapter.addFragment(DetailsMovieInfoFragment(movieLiveData), "Инфо")

        detailsMovieViewPagerAdapter.addFragment(DetailsMovieCastFragment(movieLiveData), "Cast")

        viewPager.adapter = detailsMovieViewPagerAdapter
    }

    private fun setupTabLayout() {
        tabLayout.setupWithViewPager(viewPager)
    }



    private fun settingCollapsingToolbarLayout() {
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE)
        collapsingToolbarLayout.setCollapsedTitleTextColor(resources.getColor(R.color.color_dark_gray))
    }

    private fun setUpTransparentStatusBar(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

}