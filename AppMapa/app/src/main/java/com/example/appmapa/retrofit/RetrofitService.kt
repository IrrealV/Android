package com.example.appmapa.retrofit

import com.example.appmapa.dataclass.Lugar
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("getJson.php")
    suspend fun getAllPlaces() : Response<ArrayList<Lugar>>

}