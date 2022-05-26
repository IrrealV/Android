package com.estech.appcontactos.domain.room

import com.estech.appcontactos.domain.models.Contacto


/**
 * Created by sergi on 13/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class Repositorio(private val contactosDao: ContactosDao) {


    val todosContacto = contactosDao.getAllContacto()

    suspend fun insertarContacto(contacto: Contacto){
        contactosDao.insertarContacto(contacto)
    }

    suspend fun eliminarContacto(contacto: Contacto){
        contactosDao.eliminaContacto(contacto)
    }

    suspend fun eliminarTodos(){
        contactosDao.eliminarAllContactos()
    }

}