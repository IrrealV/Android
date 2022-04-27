package com.example.appvalorant.retrofit

class Repositorio {
    private val retrofitService = RetrofitHelper.getRetrofit()

    suspend fun todosLosPersonajes() = retrofitService.getAllCharacter()
}