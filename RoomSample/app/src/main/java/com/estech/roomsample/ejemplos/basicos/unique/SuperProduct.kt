package com.estech.roomsample.ejemplos.basicos.unique

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

@Entity(
    indices = [Index(
        value = ["codigoBarras, fechaFabricacion"],
        unique = true
    )]
)
data class SuperProduct(
    @PrimaryKey val id: Int = 0,
    val nombre: String,
    val cantidad: Int,
    val codigoBarras: Int,
    val fechaFabricacion: String
)