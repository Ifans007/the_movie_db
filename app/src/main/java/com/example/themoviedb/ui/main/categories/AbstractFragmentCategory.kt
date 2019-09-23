package com.example.themoviedb.ui.main.categories

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.themoviedb.InitDatabase
import com.example.themoviedb.R
import com.example.themoviedb.database.entities.moviescategory.MovieCategoryIdTable
import com.example.themoviedb.database.repositories.MovieCategoryRepository
import com.example.themoviedb.retrofitservice.requests.models.MoviesRequest
import com.example.themoviedb.ui.OnClickListenerMovie
import com.example.themoviedb.ui.main.categories.inflater.MovieCategoryAdapter
import com.example.themoviedb.ui.main.categories.inflater.MovieCategoryViewModel
import com.example.themoviedb.ui.main.categories.inflater.ViewModelMovieCategoryFactory
import com.example.themoviedb.ui.moviedescription.MovieDetailsActivity

abstract class AbstractFragmentCategory : Fragment(), OnClickListenerMovie {

    private val GRID_COLUMNS_PORTRAIT = 1
    private val GRID_COLUMNS_LANDSCAPE = 2

    private lateinit var mainView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var viewModel: MovieCategoryViewModel
    private lateinit var movieAdapter: MovieCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mainView = inflater.inflate(R.layout.fragment_movie_category, container, false)

        initView()
        initRecyclerView()

        setSwipeRefreshLayoutListener()

        getPopularData()

        return mainView
    }

    private fun initView() {
        recyclerView = mainView.findViewById(R.id.fragment_movie_category_recycler_view)
        swipeRefreshLayout = mainView.findViewById(R.id.fragment_movie_category_swipe_refresh)
        swipeRefreshLayout.isRefreshing = true
    }

    private fun initRecyclerView() {
        configureRecyclerAdapter(resources.configuration.orientation)

        viewModel = ViewModelProviders.of(this,
            ViewModelMovieCategoryFactory(
                MovieCategoryRepository(this@AbstractFragmentCategory)
            )
        )
            .get(MovieCategoryViewModel::class.java)

        movieAdapter = MovieCategoryAdapter(this)
        recyclerView.adapter = movieAdapter

        viewModel.nowShowing.observe(this, Observer<PagedList<MovieCategoryIdTable>> {
            movieAdapter.submitList(it)

            if (it.isNotEmpty()) {
                swipeRefreshLayout.isRefreshing = false
            }
        })

        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            swipeRefreshLayout.isRefreshing = false
        })
    }

    private fun configureRecyclerAdapter(orientation: Int) {
        val isPortrait = orientation == Configuration.ORIENTATION_PORTRAIT
        gridLayoutManager = GridLayoutManager(context, if (isPortrait) GRID_COLUMNS_PORTRAIT else GRID_COLUMNS_LANDSCAPE)
        recyclerView.layoutManager = gridLayoutManager
    }

    private fun setSwipeRefreshLayoutListener() {
        swipeRefreshLayout.setOnRefreshListener { refresh() }
    }

    private fun refresh() {
        InitDatabase.refresh()
        getPopularData()
    }

    private fun getPopularData() {
        viewModel.getPopular("RU")
        movieAdapter.submitList(null)
    }

    override fun onClickListenerMovie(movieId: Int) {
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.putExtra("movieId",movieId)
        context!!.startActivity(intent)
    }

    abstract fun getRequest(page: Int,
                            onSuccess: (moviesRequest: MoviesRequest) -> Unit,
                            onError: (error: String) -> Unit)

    fun getSimpleName(): String {
        return javaClass.simpleName
    }
}