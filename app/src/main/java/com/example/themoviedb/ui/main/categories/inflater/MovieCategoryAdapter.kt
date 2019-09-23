package com.example.themoviedb.ui.main.categories.inflater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviedb.R
import com.example.themoviedb.database.DatabaseApp
import com.example.themoviedb.database.entities.moviescategory.MovieCategoryIdTable
import com.example.themoviedb.ui.OnClickListenerMovie

class MovieCategoryAdapter(private val movieCategoryFragmentListener: OnClickListenerMovie) : PagedListAdapter<MovieCategoryIdTable, RecyclerView.ViewHolder>(REPOSITORY_COMPARATOR) {

    private val databaseApp = DatabaseApp.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.movie_single_item, parent, false)

        return MovieCategoryViewHolder(
            view,
            parent.context,
            movieCategoryFragmentListener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val movieCategoryIdTable: MovieCategoryIdTable? = getItem(position)

        if (movieCategoryIdTable != null) {
            val movieViewHolder = holder as MovieCategoryViewHolder
            movieViewHolder.bindMovieCategoryData(movieCategoryIdTable, databaseApp)
        } else {
            notifyItemRemoved(position)
        }
    }

    companion object {
        private val REPOSITORY_COMPARATOR = object : DiffUtil.ItemCallback<MovieCategoryIdTable>() {
            override fun areItemsTheSame(oldItem: MovieCategoryIdTable, newItem: MovieCategoryIdTable): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieCategoryIdTable, newItem: MovieCategoryIdTable): Boolean {
                return oldItem == newItem
            }
        }
    }
}