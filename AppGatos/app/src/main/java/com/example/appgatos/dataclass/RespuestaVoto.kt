package com.example.appgatos.dataclass


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class RespuestaVoto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("message")
    val message: String
) : Parcelable