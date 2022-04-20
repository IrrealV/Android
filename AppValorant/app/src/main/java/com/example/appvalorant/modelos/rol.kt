package com.example.appvalorant.modelos

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class rol (
    @SerializedName("displayName")
        val Nrol: String,

    @SerializedName("description")
    val descriptcion: String,

    @SerializedName("displayIcon")
    val icono: String

    ): Parcelable