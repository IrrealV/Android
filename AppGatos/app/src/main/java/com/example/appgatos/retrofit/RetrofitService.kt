package com.example.appgatos.retrofit

import com.example.appgatos.dataclass.EnvioVoto
import com.example.appgatos.dataclass.Gato
import com.example.appgatos.dataclass.RespuestaVoto
import com.example.appgatos.dataclass.Voto
import retrofit2.Response
import retrofit2.http.*

const val Token = "d312a459-7418-442a-880a-606aa4acc5a4"

interface RetrofitService {

    @Headers("x-api-key:$Token")
    @GET("breeds")
    suspend fun getCatsList() : Response<ArrayList<Gato>>

    @Headers("x-api-key:$Token")
    @GET("votes")
    suspend fun getListaVotos(@Query("sub_id") nombre: String) : Response<List<Voto>>

    @Headers("x-api-key:$Token", "Content-Type:application/json")
    @POST("votes")
    suspend fun postVotos(@Body envioVoto: EnvioVoto) : Response <RespuestaVoto>

}