package com.estech.retrofitsample.modelos

import com.google.gson.annotations.SerializedName


/**
 * Created by sergi on 19/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

data class Respuesta(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<Personaje>
)