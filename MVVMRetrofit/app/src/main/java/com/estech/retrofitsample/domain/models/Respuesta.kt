package com.estech.retrofitsample.domain.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Respuesta(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: ArrayList<Personaje>
) : Parcelable