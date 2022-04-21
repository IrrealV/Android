package com.example.appvalorant

import android.app.Person
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appvalorant.databinding.VistaPersonajeBinding
import com.example.appvalorant.modelos.Personaje
import java.net.URI
import java.net.URL

class PersonajeAdapter(val personaje:List<Personaje>) :
    RecyclerView.Adapter<PersonajeAdapter.MiCelda>() {

        inner class MiCelda(val binding: VistaPersonajeBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = VistaPersonajeBinding.inflate(layoutInflater,parent,false)
        return  MiCelda(binding)
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val personaje: Personaje = personaje.get(position)
        holder.binding.itNombre.text = personaje.nombre
        Glide.with(holder.itemView).load(personaje.imagen).into(holder.binding.ivPersonaje)
        Glide.with(holder.itemView).load(personaje.fondo).into(holder.binding.ivFondo)

        when (personaje.rol?.Nrol){
            "Iniciador" -> Glide.with(holder.itemView).load(personaje.rol.icono).into(holder.binding.ivIcono)
            "Duelista" -> Glide.with(holder.itemView).load(personaje.rol.icono).into(holder.binding.ivIcono)
            "Centinela" -> Glide.with(holder.itemView).load(personaje.rol.icono).into(holder.binding.ivIcono)
            "Controlador" -> Glide.with(holder.itemView).load(personaje.rol.icono).into(holder.binding.ivIcono)
            else -> holder.binding.ivIcono.setImageResource(R.drawable.ic_launcher_foreground)
        }

    }
    override fun getItemCount(): Int {
        return personaje.size
    }
}