package com.example.themoviedb.fragments.popular.inflater

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.themoviedb.database.repositories.PopularRepository

class ViewModelPopularFactory(private val repository: PopularRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PopularViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}