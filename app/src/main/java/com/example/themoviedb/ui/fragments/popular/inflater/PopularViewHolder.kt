package com.example.themoviedb.ui.fragments.popular.inflater

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.themoviedb.R
import com.example.themoviedb.Resources
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.entities.CommonInfoMoviesTable
import com.example.themoviedb.database.entities.moviescategory.PopularMoviesIdTable
import com.example.themoviedb.ui.OnClickListenerMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class PopularViewHolder(
    itemView: View,
    private val context: Context,
    private val popularMoviesFragmentListener: OnClickListenerMovie
) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var movieTitle: TextView
    var movieRating: RatingBar
    var movieType: TextView
    var movieReleaseDate: TextView
    var moviePoster: ImageView
    var movieOverView: TextView
    private var thisMovie: CommonInfoMoviesTable? = null

    init{
        movieTitle = itemView.findViewById(R.id.single_item_movie_title)
        movieRating = itemView.findViewById(R.id.single_item_movie_rating)
        movieType = itemView.findViewById(R.id.single_item_movie_type)
        movieReleaseDate = itemView.findViewById(R.id.single_item_movie_release_date)
        moviePoster = itemView.findViewById(R.id.single_item_movie_image)
        movieOverView = itemView.findViewById(R.id.single_item_movie_overview)

        itemView.setOnClickListener(this)
    }

    fun bindPopularData(
        popularMoviesIdTable: PopularMoviesIdTable,
        databaseApp: DatabaseApp
    ) {

        runBlocking {
            val movieDeferred = async(Dispatchers.IO) { getMovie(popularMoviesIdTable, databaseApp) }.await()
            fillView(movieDeferred)
        }

    }

    private fun getMovie(
        popularMoviesIdTable: PopularMoviesIdTable,
        databaseApp: DatabaseApp
    ): CommonInfoMoviesTable? {

        return databaseApp.commonInfoMoviesDao().getById(popularMoviesIdTable.movieId)
    }


    private fun fillView(movie: CommonInfoMoviesTable?) {

        if (movie != null) {

            thisMovie = movie

            movieTitle.text = movie.title
            movieRating.rating = movie.voteAverage.div(2)
            movieReleaseDate.text = movie.releaseDate
            movieOverView.text = movie.overview

            movieType.text = movie.genres

            Glide.with(context).load(Resources.buildImagePosterUrl(movie.posterPath)).thumbnail(0.05f)
                .transition(DrawableTransitionOptions.withCrossFade()).into(moviePoster)

        }
    }

        override fun onClick(v: View?) {
        val position:Int = adapterPosition
        if (position != RecyclerView.NO_POSITION){
            popularMoviesFragmentListener.onClickListenerMovie(thisMovie!!.movieId)
        }
    }
}