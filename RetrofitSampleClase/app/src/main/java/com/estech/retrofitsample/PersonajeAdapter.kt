package com.estech.retrofitsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.estech.retrofitsample.modelos.Personaje
import com.estech.retrofitsample.databinding.VistaCeldaBinding


/**
 * Created by sergi on 05/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class PersonajeAdapter(val personajes: List<Personaje>) :
    RecyclerView.Adapter<PersonajeAdapter.MiCelda>() {

    inner class MiCelda(val binding: VistaCeldaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = VistaCeldaBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val personaje: Personaje = personajes.get(position)
        holder.binding.tvName.text = personaje.name

        Glide.with(holder.itemView).load(personaje.image).into(holder.binding.ivImage)
//        Picasso.get().load(personaje.getImage()).into(holder.ivImage)

        holder.itemView.setOnClickListener {
            val bundle = bundleOf("personaje" to personaje)
            val navigation = holder.itemView.findNavController()
            navigation.navigate(R.id.action_fragmentLista_to_fragmentPersonaje, bundle)
        }

        when (personaje.status) {
            "Alive" -> holder.binding.ivCirculo.setImageResource(R.drawable.circle_green)
            "Dead" -> holder.binding.ivCirculo.setImageResource(R.drawable.circle_red)
            else -> holder.binding.ivCirculo.setImageResource(R.drawable.circle_yellow)
        }
    }

    override fun getItemCount(): Int {
        return personajes.size
    }
}