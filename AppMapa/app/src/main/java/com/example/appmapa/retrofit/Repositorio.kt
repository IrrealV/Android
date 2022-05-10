package com.example.appmapa.retrofit

class Repositorio {
    private val retrofitService = RetrofitHelper.getRetrofit()

    suspend fun todosLosLugares() = retrofitService.getAllPlaces()
}