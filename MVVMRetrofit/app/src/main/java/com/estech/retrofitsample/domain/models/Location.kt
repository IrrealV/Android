package com.estech.retrofitsample.domain.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Location(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) : Parcelable