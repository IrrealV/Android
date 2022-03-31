package com.estech.viewpagersample.galeria

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kcount.databinding.CeldaParaGaleriaBinding


class AdapterGaleria(val listaImagenes: List<Int>) : RecyclerView.Adapter<AdapterGaleria.MiCelda>() {

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