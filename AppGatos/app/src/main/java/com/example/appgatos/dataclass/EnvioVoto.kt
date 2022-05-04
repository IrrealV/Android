package com.example.appgatos.dataclass


import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EnvioVoto(
    @SerializedName("image_id")
    val imageId: String?,
    @SerializedName("sub_id")
    val subId: String?,
    @SerializedName("value")
    val value: Int?
) : Parcelable