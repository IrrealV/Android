package com.example.appvalorant.retrofit

import com.example.appvalorant.modelos.Personaje
import com.example.appvalorant.modelos.Respuesta
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("agents")
   suspend fun getAllCharacter() : Response<Respuesta>
}