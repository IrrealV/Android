package com.example.appvalorant.modelos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Personaje (
    @SerializedName("displayName")
    val nombre : String,

    @SerializedName("fullPortrait")
    val imagen : String,

    @SerializedName("background")
    val fondo: String,

    @SerializedName("role")
    val rol : rol,



) : Parcelable
