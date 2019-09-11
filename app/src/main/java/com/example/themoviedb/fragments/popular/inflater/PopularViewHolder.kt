package com.example.themoviedb.fragments.popular.inflater

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.themoviedb.R
import com.example.themoviedb.database.entities.PopularTable

class PopularViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

    var movieTitle: TextView
    var movieRating: RatingBar
    var movieType: TextView
    var movieReleaseDate: TextView
    var moviePoster: ImageView
    var movieOverView: TextView
    private var movie: PopularTable? = null

    init{
        movieTitle = itemView!!.findViewById(R.id.single_item_movie_title)
        movieRating = itemView.findViewById(R.id.single_item_movie_rating)
        movieType = itemView.findViewById(R.id.single_item_movie_type)
        movieReleaseDate = itemView.findViewById(R.id.single_item_movie_release_date)
        moviePoster = itemView.findViewById(R.id.single_item_movie_image)
        movieOverView = itemView.findViewById(R.id.single_item_movie_overview)

    }


    fun bindPopularData(movie: PopularTable?) {

        if (movie == null) {
            return
        } else {

            this.movie = movie

            movieTitle.text = movie.title
            movieRating.rating = movie.voteAverage!!.div(2)
            movieReleaseDate.text = movie.releaseDate!!
            movieOverView.text = movie.overview
            movieType.text = movie.genreString

            Glide.with(context).load(buildImageUrl(movie.posterPath!!)).thumbnail(0.05f)
                .transition(DrawableTransitionOptions.withCrossFade()).into(moviePoster)
        }

    }

    private fun buildImageUrl(path: String): String {
        return "http://image.tmdb.org/t/p/w342" + path
    }
}