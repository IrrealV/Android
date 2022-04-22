package com.example.appvalorant.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("character")
   suspend fun getAllCharacter() : Response<Respuesta>
}