package com.estech.roomsample.ejemplos.relaciones.variosavarios

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

data class CompradorConProductos(
    @Embedded val comprador: Comprador,
    @Relation(
        parentColumn = "compradorId",
        entityColumn = "productoId",
        associateBy = Junction(CompradorAndProducto::class)
    )
    val productos: List<Producto>
)
