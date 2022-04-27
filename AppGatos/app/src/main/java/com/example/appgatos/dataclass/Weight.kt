package com.example.appgatos.dataclass


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Weight(
    val imperial: String,
    val metric: String
) : Parcelable