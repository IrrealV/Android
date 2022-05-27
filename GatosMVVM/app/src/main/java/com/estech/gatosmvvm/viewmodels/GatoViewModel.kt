package com.estech.gatosmvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.estech.gatosmvvm.modelos.enviarvoto.SendVote
import com.estech.gatosmvvm.modelos.listagatos.Breed
import com.estech.gatosmvvm.retrofit.Repositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class GatoViewModel(private val repositorio : Repositorio) : ViewModel() {

    val AllRazas: Response<LiveData<List<Breed>>> = repositorio.todasRazas

    fun enviarVoto(vote: SendVote){
        CoroutineScope(Dispatchers.IO).launch {
            repositorio.enviarVoto(vote)
        }
    }

    fun recibirListaVotos(usuario: String){
        CoroutineScope(Dispatchers.IO).launch {
            repositorio.recibirListaVotos(usuario)
        }
    }

    fun eliminarVoto(id: String){
        CoroutineScope(Dispatchers.IO).launch {
            repositorio.eliminarVoto(id)
        }
    }

    class MyViewModelFactory(private val repository: Repositorio) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(Repositorio::class.java)
                .newInstance(repository)
        }

    }
}