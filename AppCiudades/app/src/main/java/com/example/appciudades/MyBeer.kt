package com.example.appciudades

import android.app.Application
import com.example.appciudades.dominio.room.MyBD
import com.example.appciudades.dominio.room.Repositorio

class MyBeer: Application(){
    val database by lazy { MyBD.getDatabase(this) }
    val repositorio by lazy { Repositorio(database.BeerDao()) }
}