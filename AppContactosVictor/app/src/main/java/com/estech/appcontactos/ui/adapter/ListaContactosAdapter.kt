package com.estech.appcontactos.ui.adapter

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.estech.appcontactos.MyApp
import com.estech.appcontactos.databinding.ActivityMainBinding
import com.estech.appcontactos.databinding.CeldaBinding
import com.estech.appcontactos.domain.models.Contacto
import com.estech.appcontactos.domain.room.ContactosDao
import com.estech.appcontactos.domain.room.MyDataBase
import com.estech.appcontactos.domain.room.Repositorio
import com.estech.appcontactos.viewmodel.MyViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class ListaContactosAdapter: RecyclerView.Adapter<ListaContactosAdapter.Micelda>() {
        inner class Micelda(val binding: CeldaBinding ):RecyclerView.ViewHolder(binding.root)

    private val listaContacto = ArrayList<Contacto>()
    private val miapp = MyApp()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Micelda {
        val inflater = LayoutInflater.from(parent.context)
        val  binding = CeldaBinding.inflate(inflater,parent,false)
        return Micelda(binding)
    }

    override fun onBindViewHolder(holder: Micelda, position: Int) {
        val persona = listaContacto[position]
        val bind = holder.binding

        bind.Perona.text = persona.nombre
        val vm= MyViewModel(miapp.repositor)

        bind.celdaCont.setOnLongClickListener {
            vm.eliminarContacto(persona)
            notifyItemRemoved(position)
            true
        }



    }

    override fun getItemCount(): Int {
        return listaContacto.size
    }

    fun updateList(lista: List<Contacto>){
        listaContacto.clear()
        listaContacto.addAll(lista)
        notifyItemRangeChanged(lista.size-1, lista.size)


    }

}



