package com.estech.retrofitsample.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by sergi on 19/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

object RetrofitHelper {

    var retrofitService: RetrofitService? = null

    fun getRetrofit(): RetrofitService {

        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.pokemontcg.io/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClient())
                .build()

            retrofitService = retrofit.create(RetrofitService::class.java)
        }

        return retrofitService!!
    }

    /**
     * Initialize OkhttpClient with our interceptor
     */
    private fun okhttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}