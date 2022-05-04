package com.example.appgatos.retrofit

import com.example.appgatos.dataclass.EnvioVoto

class Repositorio {
    private val retrofitService = RetrofitHelper.getRetrofit()

    suspend fun todosLosGatos() = retrofitService.getCatsList()
    suspend fun todosLosVotos() = retrofitService.getListaVotos(String())
    suspend fun enviarVoto(voto: EnvioVoto) = retrofitService.postVotos(voto)
}