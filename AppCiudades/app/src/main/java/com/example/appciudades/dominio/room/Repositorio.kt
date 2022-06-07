package com.example.appciudades.dominio.room

import androidx.lifecycle.LiveData
import com.example.appciudades.dominio.models.Cerveza

class Repositorio(private val BeerDao: BeerDao) {


    val allBeer = BeerDao.allCervezas()


    suspend fun addCerveza(cerveza: Cerveza){
        BeerDao.addcerveza(cerveza)
    }

    suspend fun delCerveza(cerveza: Cerveza){
        BeerDao.delOneCerveza(cerveza)
    }
    suspend fun oneBeer(id:Int): Cerveza{
       return BeerDao.oneCerveza(id)
    }

    suspend fun eliminarTodos(){
        BeerDao.delAllCerveza()
    }

}