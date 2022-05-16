package com.estech.roomsample

import android.app.Application
import com.estech.roomsample.repository.MovieRepository
import com.estech.roomsample.room.MovieDatabase


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class MyApplication: Application() {
    val database by lazy { MovieDatabase.getDatabase(this) }
    val repository by lazy { MovieRepository(database.movieDao()) }
}