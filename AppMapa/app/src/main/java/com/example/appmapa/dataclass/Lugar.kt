package com.example.appmapa.dataclass


import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Lugar(
    @SerializedName("direccion")
    val direccion: String?,
    @SerializedName("Horario")
    val horario: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("Lat")
    val lat: String?,
    @SerializedName("Lng")
    val lng: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("telefono")
    val telefono: String?,
    @SerializedName("Texto")
    val texto: String?,
    @SerializedName("type")
    val type: Int?,
    @SerializedName("Web")
    val web: String?
) : Parcelable

