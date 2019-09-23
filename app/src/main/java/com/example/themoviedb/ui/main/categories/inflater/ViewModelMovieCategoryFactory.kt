package com.example.themoviedb.ui.main.categories.inflater

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.database.repositories.MovieCategoryRepository

class ViewModelMovieCategoryFactory(private val repository: MovieCategoryRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieCategoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieCategoryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}