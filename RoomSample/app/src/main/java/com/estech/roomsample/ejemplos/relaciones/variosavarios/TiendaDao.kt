package com.estech.roomsample.ejemplos.relaciones.variosavarios

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

@Dao
interface TiendaDao {

    @Transaction
    @Query("SELECT * FROM Comprador")
    fun getCompradorConProductos() : LiveData<List<CompradorConProductos>>
}