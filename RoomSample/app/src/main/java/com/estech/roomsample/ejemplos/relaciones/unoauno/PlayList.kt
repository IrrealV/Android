package com.estech.roomsample.ejemplos.relaciones.unoauno

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

@Entity
data class PlayList(
    @PrimaryKey val listId: Int,
    val userOwnerId: Int,
    val listName: String
)
