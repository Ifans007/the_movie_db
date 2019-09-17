package com.example.themoviedb.ui.fragments.popular.inflater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.entities.moviescategory.PopularMoviesIdTable
import com.example.themoviedb.ui.OnClickListenerMovie

class PopularAdapter(private val popularMoviesFragmentListener: OnClickListenerMovie) : PagedListAdapter<PopularMoviesIdTable, RecyclerView.ViewHolder>(REPOSITORY_COMPARATOR) {

    private val databaseApp = DatabaseApp.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.movie_single_item, parent, false)

        return PopularViewHolder(
            view,
            parent.context,
            popularMoviesFragmentListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val popularMoviesIdTable: PopularMoviesIdTable? = getItem(position)

        if (popularMoviesIdTable != null) {
            val movieViewHolder = holder as PopularViewHolder
            movieViewHolder.bindPopularData(popularMoviesIdTable, databaseApp)
        } else {
            notifyItemRemoved(position)
        }
    }

    companion object {
        private val REPOSITORY_COMPARATOR = object : DiffUtil.ItemCallback<PopularMoviesIdTable>() {
            override fun areItemsTheSame(oldItem: PopularMoviesIdTable, newItem: PopularMoviesIdTable): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: PopularMoviesIdTable, newItem: PopularMoviesIdTable): Boolean {
                return oldItem == newItem
            }
        }
    }
}