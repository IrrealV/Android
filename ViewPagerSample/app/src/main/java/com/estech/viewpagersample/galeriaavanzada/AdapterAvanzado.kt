package com.estech.viewpagersample.galeriaavanzada

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.estech.viewpagersample.R
import com.estech.viewpagersample.databinding.CeldaParaAvanzadaBinding


/**
 * Created by sergi on 21/03/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class AdapterAvanzado(val listaPeliculas: List<Pelicula>) : RecyclerView.Adapter<AdapterAvanzado.EstaCelda>() {

    inner class EstaCelda(val binding: CeldaParaAvanzadaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstaCelda {
        val layoutinflater = LayoutInflater.from(parent.context)
        val binding = CeldaParaAvanzadaBinding.inflate(layoutinflater, parent, false)
        val layoutCelda = EstaCelda(binding)
        return layoutCelda
    }

    override fun onBindViewHolder(holder: EstaCelda, position: Int) {
        val pelicula = listaPeliculas[position]
        Glide.with(holder.itemView)
            .load(pelicula.imagen)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.binding.imageviewCelda)

        holder.binding.textViewTitle.text = pelicula.titulo
    }

    override fun getItemCount(): Int {
        return listaPeliculas.size
    }
}