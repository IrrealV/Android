package com.estech.roomsample.ejemplos.basicos.tablanormal

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

@Entity(tableName = "producto")
data class Producto(
    @PrimaryKey()
    val id: Int,
    @ColumnInfo(name = "nombre")
    val nombreProducto: String,
    val cantidad: Int,
    val marca: String,
    val necesario: Boolean,
    @Ignore
    val imagen: Bitmap
)