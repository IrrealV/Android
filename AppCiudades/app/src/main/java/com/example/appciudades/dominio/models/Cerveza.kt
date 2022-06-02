package com.example.appciudades.dominio.models

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

@Entity
data class Cerveza(

    val nombre: String,
    val ciudad: String,
    val pais: String,
    val grados: Int,
//    val img: Image?,
    val probado: Boolean,
    val Ubi: String,

    ){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
