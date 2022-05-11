package com.estech.recyclerviewexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estech.recyclerviewexample.model.Jugador
import com.estech.recyclerviewexample.model.Repository


/**
 * Created by sergi on 10/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class JugadoresViewModel : ViewModel() {

    private val repository by lazy {
        Repository()
    }
    val listaJugadores: MutableLiveData<ArrayList<Jugador>> by lazy {
        MutableLiveData<ArrayList<Jugador>>()
    }
    var error = MutableLiveData<String>()

    init {
        listaJugadores.value = repository.crearLista()
    }

    fun addJugador(nombre: String, posicion: String, dorsal: String) {
        if (nombre.isNotEmpty() && posicion.isNotEmpty() && dorsal.isNotEmpty()) {
            val jugador = Jugador(nombre, posicion, dorsal.toInt())
            val list = listaJugadores.value
            list.let {
                it?.add(jugador)
                listaJugadores.value = it
            }

        } else
            error.value = "Faltan datos"
    }

}