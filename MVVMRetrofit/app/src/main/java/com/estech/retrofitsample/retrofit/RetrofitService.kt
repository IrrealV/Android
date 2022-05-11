package com.estech.retrofitsample.retrofit

import com.estech.retrofitsample.models.Respuesta
import retrofit2.Response
import retrofit2.http.*


/**
 * Created by sergi on 19/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

interface RetrofitService {

    @GET("character")
    suspend fun damePersonajes(): Response<Respuesta>

}