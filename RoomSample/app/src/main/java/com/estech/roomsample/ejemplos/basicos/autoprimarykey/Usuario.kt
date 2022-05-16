package com.estech.roomsample.ejemplos.basicos.autoprimarykey

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

@Entity
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val password: String,
    @ColumnInfo(name = "hijos")
    val numHijos: String
)
