package com.example.themoviedb.ui.fragments.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.themoviedb.R
import com.example.themoviedb.ui.OnClickListenerMovie

class TopRatedFragment : Fragment(), OnClickListenerMovie{

    private lateinit var mainView: View


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainView = inflater.inflate(R.layout.fragment_popular_movies, container, false)

        return mainView
    }

    override fun onClickListenerMovie(movieId: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}