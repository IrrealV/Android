package com.example.appgatos.retrofit

import com.example.appgatos.dataclass.*
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

    @Headers("x-api-key:$Token")
    @DELETE("votes/{sub_id}")
    suspend fun deleteVotos(@Path("sub_id")id : Int): Response<DelVoto>

}