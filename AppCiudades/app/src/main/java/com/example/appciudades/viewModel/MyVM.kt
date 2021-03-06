package com.example.appciudades.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appciudades.dominio.models.Cerveza
import com.example.appciudades.dominio.room.Repositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyVM(private val repositorio: Repositorio): ViewModel(){


    val todoCerveza: LiveData<List<Cerveza>> = repositorio.allBeer

    fun insertarCerveza(cerveza: Cerveza){
        CoroutineScope(Dispatchers.IO).launch {
            repositorio.addCerveza(cerveza)
        }
    }

     suspend fun unaBeer(id:Int):Cerveza{
        CoroutineScope(Dispatchers.IO).launch {
            repositorio.oneBeer(id)
           return@launch
        }
        return repositorio.oneBeer(id)
    }

    fun eliminarCerveza(cerveza: Cerveza){
        CoroutineScope(Dispatchers.IO).launch {
            repositorio.delCerveza(cerveza)
        }
    }

    fun eliminarAllCerveza(){
        CoroutineScope(Dispatchers.IO).launch {
            repositorio.eliminarTodos()
        }
    }

    class MyViewModelFactory(private val repository: Repositorio) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            return modelClass.getConstructor(Repositorio::class.java)
                .newInstance(repository)
        }

    }
}