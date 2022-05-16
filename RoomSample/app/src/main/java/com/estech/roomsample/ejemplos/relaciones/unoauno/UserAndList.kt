package com.estech.roomsample.ejemplos.relaciones.unoauno

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

data class UserAndList(
    @Embedded val usuario: Usuario,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId"
    )
    val playList: PlayList
)