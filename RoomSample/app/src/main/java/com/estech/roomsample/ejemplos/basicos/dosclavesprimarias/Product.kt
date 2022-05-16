package com.estech.roomsample.ejemplos.basicos.dosclavesprimarias

import androidx.room.Entity


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

@Entity(primaryKeys = ["name", "brand"])
data class Product(
    val name: String,
    val brand: String
)
