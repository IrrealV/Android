package com.estech.roomsample.ejemplos.relaciones.objetosincorporados

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

@Entity
data class User(
    @PrimaryKey val id: Int,
    val nombre: String,
    val apellidos: String,
    @Embedded val address: Address?
)
