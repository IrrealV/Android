package com.estech.gatosmvvm.retrofit

import androidx.lifecycle.LiveData

import com.estech.gatosmvvm.modelos.eliminarvoto.ResponseDeleteVote
import com.estech.gatosmvvm.modelos.enviarvoto.ResponseVote
import com.estech.gatosmvvm.modelos.enviarvoto.SendVote
import com.estech.gatosmvvm.modelos.listagatos.Breed
import retrofit2.Response
import retrofit2.http.*


/**
 * Created by sergi on 19/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

const val key = "x-api-key: 0c64027f-e9e8-4ab7-a1fc-d307611ecb5f"
interface RetrofitService {


    @Headers(key)
    @GET("breeds")
    fun getRazas():Response<LiveData<List<Breed>>>

    @Headers(key,"Content-Type: application/json")
    @POST("votes")
    suspend fun sendVote(@Body vote: SendVote): Response<LiveData<ResponseVote>>

    @Headers(key)
    @GET("votes")
    suspend fun getVotesList(@Query("sub_id") usuario: String): Response<LiveData<ArrayList<Breed>>>

    @Headers(key)
    @DELETE("votes/{id}")
    suspend fun deleteVote(@Path("id") id: String): Response<LiveData<ResponseDeleteVote>>

}


