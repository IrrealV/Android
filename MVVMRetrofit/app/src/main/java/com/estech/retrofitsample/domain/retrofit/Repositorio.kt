package com.estech.retrofitsample.domain.retrofit


/**
 * Created by sergi on 19/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class Repositorio {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun dameTodosPersonajes(pagina: Int) = retrofit.damePersonajes(pagina)
}