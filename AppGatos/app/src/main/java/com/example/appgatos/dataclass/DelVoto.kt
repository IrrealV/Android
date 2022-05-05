package com.example.appgatos.dataclass


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class DelVoto(
    @SerializedName("level")
    val level: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
) : Parcelable