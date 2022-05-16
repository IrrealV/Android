package com.estech.roomsample.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by sergi on 10/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

@Entity(tableName = "movie_items")
data class MovieEntity(
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "year") var year: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
