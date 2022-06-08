package com.example.appciudades.dominio.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng


@Entity
data class Cerveza(

    val nombre: String,
    val ciudad: String,
    val pais: String,
    val grados: Double,
    val latitud: Double,
    val longitud: Double,
    val img: String?,
    val probado: Boolean,
    val Ubi: String,

    ){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
