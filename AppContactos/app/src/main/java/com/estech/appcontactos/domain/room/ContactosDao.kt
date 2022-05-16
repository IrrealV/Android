package com.estech.appcontactos.domain.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.estech.appcontactos.domain.models.Contacto
import kotlinx.coroutines.selects.select

@Dao
interface ContactosDao {
    @Insert
    suspend fun insertarContacto(vararg contacto: Contacto)

    @Query("select * from contacto")
    fun getAllContacto():LiveData<List<Contacto>>

    @Query("select * from contacto where id = :id")
    fun getContacto(id: Int):LiveData<Contacto>

    @Delete
    suspend fun  eliminaContacto(contacto: Contacto)

    @Query("delete from Contacto where id = :id")
    suspend fun eliminaContacto2(id: Int)

    @Query("delete from contacto")
    suspend fun eliminarAllContactos()

    @Query("select * from contacto where nombre like :nombre")
    fun getNombre(nombre: String):LiveData<List<Contacto>>

    @Update
    suspend fun contactoAct(contacto: Contacto)

    @Query("select * from contacto where fav = 1")
    suspend fun favs(): LiveData<List<Contacto>>
}