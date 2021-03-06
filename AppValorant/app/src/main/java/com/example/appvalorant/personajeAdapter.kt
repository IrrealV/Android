package com.example.appvalorant


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appvalorant.databinding.VistaPersonajeBinding
import com.example.appvalorant.modelos.Personaje

class PersonajeAdapter(val personaje: ArrayList<Personaje>) :
    RecyclerView.Adapter<PersonajeAdapter.MiCelda>() {

        inner class MiCelda(val binding: VistaPersonajeBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = VistaPersonajeBinding.inflate(layoutInflater,parent,false)
        return  MiCelda(binding)
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val personaje: Personaje = personaje.get(position)

        creacion(holder,position)

        holder.itemView.setOnClickListener {
            val bundle = bundleOf("personaje" to personaje)
            val navigation = holder.itemView.findNavController()
            navigation.navigate(R.id.action_listaFragment_to_personajeFragment, bundle)
        }
    }



    override fun getItemCount(): Int {
        return personaje.size
    }


    private fun creacion(holder: MiCelda, Int: Int) {
        val personaje: Personaje = personaje.get(Int)
        holder.binding.itNombre.text = personaje.nombre
        Glide.with(holder.itemView).load(personaje.imagen).into(holder.binding.ivPersonaje)
        Glide.with(holder.itemView).load(personaje.fondo).into(holder.binding.ivFondo)
        Glide.with(holder.itemView).load(personaje.rol.icono).into(holder.binding.ivIcono)
    }


}


