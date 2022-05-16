package com.estech.roomsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estech.roomsample.repository.MovieRepository
import com.estech.roomsample.room.MovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by sergi on 10/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class MovieViewModel(val repository: MovieRepository) : ViewModel() {
    val allMovies: LiveData<List<MovieEntity>>

    init {
        allMovies = repository.allMovies
    }

    fun insert(movie: MovieEntity) = CoroutineScope(Dispatchers.IO).launch {
        repository.insert(movie)
    }

    class MyViewModelFactory(val repository: MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(MovieRepository::class.java)
                .newInstance(repository)
        }

    }
}