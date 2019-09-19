package com.example.themoviedb.ui.activity.moviedescription.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.themoviedb.R
import com.example.themoviedb.database.entities.detailsinfo.DetailsInfoMoviesTable

class DetailsMovieCastFragment(movieLiveData: MutableLiveData<DetailsInfoMoviesTable>) : Fragment() {

    private lateinit var mainView: View
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainView = inflater.inflate(R.layout.fragment_movie_details_info, container, false)

        initView()

        return mainView
    }

    private fun initView() {
        swipeRefreshLayout = mainView.findViewById(R.id.fragment_movie_details_swipe_refresh)
    }



}
