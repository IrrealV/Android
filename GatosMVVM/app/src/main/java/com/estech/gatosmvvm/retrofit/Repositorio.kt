package com.estech.gatosmvvm.retrofit

import com.estech.gatosmvvm.modelos.enviarvoto.SendVote


/**
 * Created by sergi on 19/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class Repositorio(private val retrofitService: RetrofitService) {

   val todasRazas = retrofitService.getRazas()

    suspend fun enviarVoto(vote: SendVote){
        retrofitService.sendVote(vote)
    }

    suspend fun recibirListaVotos(usuario: String){
        retrofitService.getVotesList(usuario)
    }

    suspend fun eliminarVoto(id: String){
        retrofitService.deleteVote(id)
    }



}