package com.estech.roomsample.ejemplos.basicos.tablanormal

import androidx.lifecycle.LiveData
import androidx.room.*
import com.estech.roomsample.ejemplos.relaciones.objetosincorporados.User
import com.estech.roomsample.ejemplos.relaciones.unoavarios.Song


/**
 * Created by sergi on 12/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

@Dao
interface ProductoDao {

    @Insert
    suspend fun insertaProductos(vararg productos: Producto)

    @Insert
    suspend fun insertaLista(productos: List<Producto>)

    @Update
    suspend fun actualizaProducto(vararg producto: Producto)

    @Delete
    suspend fun eliminaProducto(producto: Producto)

    @Query("SELECT * FROM producto")
    fun dameTodosProductos(): LiveData<List<Producto>>

    @Query("SELECT * FROM producto WHERE necesario = 1")
    fun dameProductosNecesarios(): LiveData<List<Producto>>

    @Query("SELECT * FROM producto WHERE cantidad > :num")
    fun productosPorCantidad(num: Int): LiveData<List<Producto>>

    @Query("SELECT * FROM producto WHERE cantidad BETWEEN :min AND :max")
    fun productosEntreCantidad(min: Int, max: Int): LiveData<List<Producto>>

    @Query("SELECT * FROM producto WHERE marca IN (:marcas)")
    fun productosPorMarca(marcas: List<String>): LiveData<List<Producto>>

    @Query("SELECT * FROM producto WHERE marca LIKE (:marca)")
    fun productosPorMarca2(marca: String): LiveData<List<Producto>>

    @Query("SELECT * FROM producto WHERE marca LIKE 'ÑAM'")
    fun productosNiam(marca: String): LiveData<List<Producto>>

    @Query(
        "SELECT * FROM song " +
                "INNER JOIN singer ON singer.singerId = singerOwnerId " +
                "WHERE singer.edad > 65"
    )
    fun dameCantantesMayores(): LiveData<List<Song>>

    @Query("SELECT * FROM usuario ORDER BY name ASC")
    fun getUsersAsc(): LiveData<List<User>>
}