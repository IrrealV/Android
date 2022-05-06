package com.example.appgatos.dataclass


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class DelVoto(
    @SerializedName("message")
    val message: String,
) : Parcelable