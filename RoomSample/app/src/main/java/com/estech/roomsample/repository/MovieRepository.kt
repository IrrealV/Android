package com.estech.roomsample.repository

import androidx.lifecycle.LiveData
import com.estech.roomsample.room.MovieDao
import com.estech.roomsample.room.MovieEntity


/**
 * Created by sergi on 10/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class MovieRepository(private val movieDao: MovieDao) {
    val allMovies: LiveData<List<MovieEntity>> = movieDao.getAll()

    suspend fun insert(movie: MovieEntity) {
        movieDao.insert(movie)
    }
}