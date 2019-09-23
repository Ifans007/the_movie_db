package com.example.themoviedb.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.themoviedb.InitDatabase
import com.example.themoviedb.R
import com.example.themoviedb.ui.MovieViewPagerAdapter
import com.example.themoviedb.ui.main.categories.fragmentscategory.NowPlayingFragment
import com.example.themoviedb.ui.main.categories.fragmentscategory.PopularFragment
import com.example.themoviedb.ui.main.categories.fragmentscategory.TopRatedFragment
import com.example.themoviedb.ui.main.categories.fragmentscategory.UpcomingFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Main)
            .launch { InitDatabase.initDatabase(this@MainActivity) }

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
        val movieViewPagerAdapter =
            MovieViewPagerAdapter(supportFragmentManager)

        movieViewPagerAdapter.addFragment(PopularFragment(), "Популярные")
        movieViewPagerAdapter.addFragment(TopRatedFragment(), "Лучшие")
        movieViewPagerAdapter.addFragment(UpcomingFragment(), "Ожидаемые")
        movieViewPagerAdapter.addFragment(NowPlayingFragment(), "Смотрят сейчас")

        viewPager.adapter = movieViewPagerAdapter
    }

    private fun setupTabLayout() {
        tabLayout.setupWithViewPager(viewPager)
    }
}
