package com.example.appciudades.dominio.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appciudades.dominio.models.Cerveza


@Dao
interface BeerDao {

    @Insert
    suspend fun addcerveza(vararg cerveza: Cerveza)

    @Query("select * from cerveza")
    fun  allCervezas():LiveData<List<Cerveza>>

    @Query("select * from cerveza where id = :id")
    fun  oneCerveza(id : Int):LiveData<Cerveza>

    @Delete
    suspend fun delOneCerveza(cerveza: Cerveza)

    @Query("delete from cerveza")
    suspend fun delAllCerveza()

    @Update
    suspend fun UpdtCerveza(cerveza: Cerveza)

}