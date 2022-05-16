package com.estech.roomsample.ejemplos.relaciones.unoavarios

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

@Entity
data class Singer(
    @PrimaryKey val singerId: Int,
    val name: String,
    val edad: Int,
    val foundation: Int
)
