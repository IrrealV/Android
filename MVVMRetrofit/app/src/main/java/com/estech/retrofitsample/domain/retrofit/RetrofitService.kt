package com.estech.retrofitsample.domain.retrofit

import com.estech.retrofitsample.domain.models.Respuesta
import retrofit2.Response
import retrofit2.http.*


/**
 * Created by sergi on 19/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

interface RetrofitService {

    @GET("character")
    suspend fun damePersonajes(@Query ("page") pagina: Int): Response<Respuesta>

}