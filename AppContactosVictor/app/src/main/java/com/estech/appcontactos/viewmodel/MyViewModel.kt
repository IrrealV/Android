package com.estech.appcontactos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estech.appcontactos.domain.models.Contacto
import com.estech.appcontactos.domain.room.Repositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


/**
 * Created by sergi on 13/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class MyViewModel(val repository: Repositorio) : ViewModel() {
    val todosContactos: LiveData<List<Contacto>>

    init{
        todosContactos = repository.todosContacto
    }

    fun insertarContacto(contacto: Contacto){
        CoroutineScope(Dispatchers.IO).launch {
            repository.insertarContacto(contacto)
        }
    }

    class MyViewModelFactory(val repository: Repositorio) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Repositorio::class.java)
                .newInstance(repository)
        }

    }
}