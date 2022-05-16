package com.estech.appcontactos

import android.app.Application
import com.estech.appcontactos.domain.room.MyDataBase
import com.estech.appcontactos.domain.room.Repositorio


/**
 * Created by sergi on 13/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class MyApp : Application() {
    val database by lazy { MyDataBase.getDatabase(this) }
    val repository by lazy { Repositorio(database.contactosDao()) }
}