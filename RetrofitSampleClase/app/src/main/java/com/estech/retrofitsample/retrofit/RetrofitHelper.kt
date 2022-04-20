package com.estech.retrofitsample.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    var retrofitService : RetrofitService ? = null

    fun getRetrofit (): RetrofitService {
        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl( "https://rickandmortyapi.com/api/" )
                .addConverterFactory( GsonConverterFactory .create())
                .build()
            retrofitService = retrofit.create(RetrofitService ::class.java)
        }
        return retrofitService !!
    }
}