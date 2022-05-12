package com.estech.gatosmvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.estech.gatosmvvm.modelos.listagatos.Breed
import com.estech.retrofitsample.retrofit.Repositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GatoViewModel: ViewModel() {
    private val repositorio = Repositorio()
    val listaRazas = MutableLiveData<ArrayList<Breed>>()
    val errorRazas = MutableLiveData<String>()

    fun getListRazas(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repositorio.getRazas()

            if(response.isSuccessful){
                val respuesta = response.body()

                respuesta?.let{
                    listaRazas.postValue(it)
                }
            }
            else{
                errorRazas.postValue(response.message())
            }
        }
    }

}
