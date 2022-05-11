package com.estech.retrofitsample.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estech.retrofitsample.models.Personaje
import com.estech.retrofitsample.retrofit.Repositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Created by sergi on 10/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class PersonajeViewModel : ViewModel() {

    private val repositorio by lazy { Repositorio() }
    val personajesLiveData: MutableLiveData<ArrayList<Personaje>> by lazy {
        MutableLiveData<ArrayList<Personaje>>()
    }
    val error: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val personajeSeleccionado by lazy { MutableLiveData<Personaje>() }

    fun getPersonajes() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repositorio.dameTodosPersonajes()

            if (response.isSuccessful) {
                val respuesta = response.body()
                val listaPersonajes = respuesta?.results
                listaPersonajes?.let {
                    personajesLiveData.postValue(it)
                }
            } else {
                error.value = response.message()
            }
        }
    }

    fun selectPersonaje(personaje: Personaje) {
        personajeSeleccionado.value = personaje
    }

}