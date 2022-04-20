package com.estech.retrofitsample.retrofit

class Repositorio {

    private val retrofitService = RetrofitHelper.getRetrofit()

    suspend fun dameTodosLosPersonajes() = retrofitService.getAllCharacter()
}