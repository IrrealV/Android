package com.estech.gatosmvvm.modelos.listavotos

import com.google.gson.annotations.SerializedName


/**
 * Created by sergi on 11/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

data class Votes(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("image_id")
    val imageId: String?,

    @SerializedName("sub_id")
    val subId: String?,

    @SerializedName("created_at")
    val createdAt: String?,

    @SerializedName("value")
    val value: Int?,

    @SerializedName("country_code")
    val countryCode: String,

    )
