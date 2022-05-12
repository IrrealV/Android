package com.estech.retrofitsample.retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by sergi on 19/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

object RetrofitHelper {

    val BASEURL = "https://api.thecatapi.com/v1/"

    var retrofitService: RetrofitService? = null

    fun getRetrofit(): RetrofitService {

        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASEURL)
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
        val interceptor = HttpLoggingInterceptor {
            Log.d("MI ETIQUETA", it)
        }
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return client
    }
}