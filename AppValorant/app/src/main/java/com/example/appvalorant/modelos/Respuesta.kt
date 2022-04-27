package com.example.appvalorant.modelos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class Respuesta(
    @SerializedName("data")
    val results: ArrayList<Personaje>
)