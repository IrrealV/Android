package com.estech.roomsample.ejemplos.relaciones.unoavarios

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

@Entity
data class Song(
    @PrimaryKey
    val id: Int,
    val titulo: String,
    val duracion: String,
    val singerOwnerId: Int
)
