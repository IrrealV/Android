package com.estech.retrofitsample.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.estech.retrofitsample.R
import com.estech.retrofitsample.databinding.VistaCeldaBinding
import com.estech.retrofitsample.models.Personaje


/**
 * Created by sergi on 05/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class PersonajeAdapter(val listener: OnItemClickListener) :
    RecyclerView.Adapter<PersonajeAdapter.MiCelda>() {

    private var personajes = ArrayList<Personaje>()

    interface OnItemClickListener {
        fun onItemClick(personaje: Personaje)
    }

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

        holder.itemView.setOnClickListener {
//            val bundle = bundleOf("personaje" to personaje)
//            val navigation = holder.itemView.findNavController()
//            navigation.navigate(R.id.action_fragmentLista_to_fragmentPersonaje, bundle)
            listener.onItemClick(personaje)
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

    fun updateList(lista: ArrayList<Personaje>) {
        personajes.clear()
        personajes.addAll(lista)
        notifyDataSetChanged()
    }
}