package com.estech.retrofitsample.retrofit

import com.estech.gatosmvvm.modelos.eliminarvoto.ResponseDeleteVote
import com.estech.gatosmvvm.modelos.enviarvoto.ResponseVote
import com.estech.gatosmvvm.modelos.enviarvoto.SendVote
import com.estech.gatosmvvm.modelos.listagatos.Breed
import com.estech.gatosmvvm.modelos.listavotos.Votes
import retrofit2.Response
import retrofit2.http.*


/**
 * Created by sergi on 19/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */


interface RetrofitService {

    @Headers("x-api-key: 0c64027f-e9e8-4ab7-a1fc-d307611ecb5f")
    @GET("breeds")
    suspend fun getRazas(): Response<ArrayList<Breed>>

    @Headers(
        "x-api-key:  0c64027f-e9e8-4ab7-a1fc-d307611ecb5f",
        "Content-Type: application/json"
    )
    @POST("votes")
    suspend fun sendVote(@Body vote: SendVote): Response<ResponseVote>

    @Headers("x-api-key: 0c64027f-e9e8-4ab7-a1fc-d307611ecb5f")
    @GET("votes")
    suspend fun getVotesList(@Query("sub_id") usuario: String): Response<ArrayList<Votes>>

    @Headers("x-api-key: 0c64027f-e9e8-4ab7-a1fc-d307611ecb5f")
    @DELETE("votes/{id}")
    suspend fun deleteVote(@Path("id") id: String): Response<ResponseDeleteVote>

}