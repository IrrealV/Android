package com.estech.roomsample.ejemplos.relaciones.variosavarios

import androidx.room.Entity


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

@Entity(primaryKeys = ["compradorId", "productoId"])
data class CompradorAndProducto(
    val compradorId: Int,
    val productoId: Int
)
