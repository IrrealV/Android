package com.estech.appcontactos.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contacto(

    val nombre: String,
    val apellido: String,
    val tel: String,
    val email: String,
    val edad: Int,
    val fav: Boolean
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
