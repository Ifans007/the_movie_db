package com.example.themoviedb.fragments.popular.inflater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.database.entities.moviescategory.PopularMoviesTable

class PopularAdapter : PagedListAdapter<PopularMoviesTable, RecyclerView.ViewHolder>(REPOSITORY_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.movie_single_item, parent, false)

        return PopularViewHolder(
            view,
            parent.context
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val movie: PopularMoviesTable? = getItem(position)

        if (movie != null) {
            val movieViewHolder = holder as PopularViewHolder
            movieViewHolder.bindPopularData(movie)
        } else {
            notifyItemRemoved(position)
        }
    }

    companion object {
        private val REPOSITORY_COMPARATOR = object : DiffUtil.ItemCallback<PopularMoviesTable>() {
            override fun areItemsTheSame(oldItem: PopularMoviesTable, newItem: PopularMoviesTable): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: PopularMoviesTable, newItem: PopularMoviesTable): Boolean {
                return oldItem == newItem
            }
        }
    }
}