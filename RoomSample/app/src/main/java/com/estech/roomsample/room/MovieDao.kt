package com.estech.roomsample.room

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 * Created by sergi on 10/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie_items ORDER BY title ASC")
    fun getAll(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg movies: MovieEntity)

    @Delete
    suspend fun delete(movie: MovieEntity)

    @Update
    suspend fun update(vararg movies: MovieEntity)

}