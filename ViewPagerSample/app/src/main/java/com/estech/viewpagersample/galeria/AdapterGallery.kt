package com.estech.viewpagersample.galeria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.estech.viewpagersample.databinding.CeldaParaGaleriaBinding


/**
 * Created by sergi on 21/03/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class AdapterGallery(val listaImagenes: List<Int>) : RecyclerView.Adapter<AdapterGallery.MiCelda>() {

    inner class MiCelda(val binding: CeldaParaGaleriaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {
        val layoutinflater = LayoutInflater.from(parent.context)
        val binding = CeldaParaGaleriaBinding.inflate(layoutinflater, parent, false)
        val layoutCelda = MiCelda(binding)
        return layoutCelda
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val idImagen = listaImagenes[position]
        holder.binding.imageviewCelda.setImageResource(idImagen)
    }

    override fun getItemCount(): Int {
        return listaImagenes.size
    }
}