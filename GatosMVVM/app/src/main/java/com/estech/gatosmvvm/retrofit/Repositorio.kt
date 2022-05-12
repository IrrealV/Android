package com.estech.retrofitsample.retrofit

import com.estech.gatosmvvm.modelos.enviarvoto.SendVote


/**
 * Created by sergi on 19/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class Repositorio {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getRazas() = retrofit.getRazas()
    suspend fun sendVote(vote: SendVote) = retrofit.sendVote(vote)
    suspend fun getVotesList(usuario: String) = retrofit.getVotesList(usuario)
    suspend fun deleteVote(id: String) = retrofit.deleteVote(id)

}