package com.example.appgatos.dataclass


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Image(
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
) : Parcelable