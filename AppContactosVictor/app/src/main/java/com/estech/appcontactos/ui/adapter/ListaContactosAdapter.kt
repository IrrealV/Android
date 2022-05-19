package com.estech.appcontactos.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.estech.appcontactos.databinding.ActivityMainBinding
import com.estech.appcontactos.databinding.CeldaBinding
import com.estech.appcontactos.domain.models.Contacto

class ListaContactosAdapter: RecyclerView.Adapter<ListaContactosAdapter.Micelda>() {
        inner class Micelda(val binding: CeldaBinding ):RecyclerView.ViewHolder(binding.root)
    private val listaContacto = ArrayList<Contacto>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Micelda {
        val inflater = LayoutInflater.from(parent.context)
        val  binding = CeldaBinding.inflate(inflater,parent,false)
        return Micelda(binding)
    }

    override fun onBindViewHolder(holder: Micelda, position: Int) {
        val persona = listaContacto[position]
        val bind = holder.binding

        bind.Perona.text = persona.nombre


    }

    override fun getItemCount(): Int {
        return listaContacto.size
    }

    fun updateList(lista: List<Contacto>){
        listaContacto.clear()
        listaContacto.addAll(lista)
        notifyItemRangeChanged(lista.size -1, lista.size)
    }
}