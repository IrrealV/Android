package com.example.appvalorant

import android.app.Person
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appvalorant.databinding.VistaPersonajeBinding
import com.example.appvalorant.modelos.Personaje

class personajeAdapter(val personaje:List<Personaje>) :
    RecyclerView.Adapter<personajeAdapter.MiCelda>() {

        inner class MiCelda(val binding: VistaPersonajeBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = VistaPersonajeBinding.inflate(layoutInflater,parent,false)
        return  MiCelda(binding)
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val personaje: Personaje = personaje.get(position)
        holder.binding.itNombre.text = personaje.nombre
    }
    override fun getItemCount(): Int {
        return personaje.size
    }
}