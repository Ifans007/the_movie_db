package com.example.themoviedb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.themoviedb.ui.fragments.MovieViewPagerAdapter
import com.example.themoviedb.ui.fragments.popular.PopularMoviesFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    private lateinit var movieViewPagerAdapter: MovieViewPagerAdapter
    private lateinit var popularMoviesFragment: PopularMoviesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InitDatabase(this)

        initView()
        setupToolBar()
        setupViewPager()
        setupTabLayout()
    }


    private fun initView() {
        toolbar = findViewById(R.id.activity_main_toolbar)
        tabLayout = findViewById(R.id.activity_main_tab_layout)
        viewPager = findViewById(R.id.activity_main_view_pager)
    }

    private fun setupToolBar() {
        toolbar.title = "TheMovieDB"
        setSupportActionBar(toolbar)
    }

    private fun setupViewPager() {
        movieViewPagerAdapter = MovieViewPagerAdapter(supportFragmentManager)

        popularMoviesFragment = PopularMoviesFragment()

        movieViewPagerAdapter.addFragment(popularMoviesFragment, "Popular")

        viewPager.adapter = movieViewPagerAdapter
    }

    private fun setupTabLayout() {
        tabLayout.setupWithViewPager(viewPager)
    }
}
