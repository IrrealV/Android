package com.estech.roomsample.ejemplos.relaciones.unoavarios

import androidx.room.Embedded
import androidx.room.Relation


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

data class SingerAndSong(
    @Embedded val singer: Singer,
    @Relation(
        parentColumn = "singerId",
        entityColumn = "singerOwnerId"
    )
    val songList: List<Song>
)
