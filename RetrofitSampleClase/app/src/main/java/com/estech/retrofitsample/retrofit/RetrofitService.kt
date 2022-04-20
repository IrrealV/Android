package com.estech.retrofitsample.retrofit

import com.estech.retrofitsample.modelos.Respuesta
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("character")
    suspend fun getAllCharacter() : Response<Respuesta>
}