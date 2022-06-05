package com.example.appciudades.dominio.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Cerveza(

    val nombre: String,
    val ciudad: String,
    val pais: String,
    val grados: Int,
    val latitud: Double,
    val longitud: Double,
//    val img: Image?,
    val probado: Boolean,
    val Ubi: String,

    ){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
