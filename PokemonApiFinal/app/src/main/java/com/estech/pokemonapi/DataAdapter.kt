package com.estech.pokemonapi

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.estech.pokemonapi.databinding.ItemListBinding
import com.estech.pokemonapi.models.Resultado


/**
 * Created by sergi on 05/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class DataAdapter(var listaInicial: ArrayList<Resultado.Data>) :
    RecyclerView.Adapter<DataAdapter.MiCelda>(), Filterable {

    private var listaCopia = listaInicial

    inner class MiCelda(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val pokemon: Resultado.Data = listaCopia.get(position)
        holder.binding.tvName.text = pokemon.name

        Glide.with(holder.itemView).load(pokemon.images.large).into(holder.binding.ivImage)

        val tipos = pokemon.types.toString()
        holder.binding.tvType.text = tipos
    }

    override fun getItemCount(): Int {
        return listaCopia.size
    }


    fun refreshList(listaPersonajes: ArrayList<Resultado.Data>) {
//        listaCopia.clear()
        listaCopia = listaPersonajes
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val palabraABuscar = p0.toString()

                if (palabraABuscar.isEmpty()) {
                    listaCopia = listaInicial
                } else {
                    listaCopia = listaInicial.filter {
                        (it.name.lowercase().contains(palabraABuscar.lowercase()) ||
                                it.types.toString().lowercase().contains(palabraABuscar.lowercase()))
                    } as ArrayList<Resultado.Data>
//                    listaCopia = listaApoyo as ArrayList<Resultado.Data>
                }
                val filterResults = FilterResults()
                filterResults.values = listaCopia
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                listaCopia = p1?.values as ArrayList<Resultado.Data>
                notifyDataSetChanged()
            }

        }
    }

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(p0: CharSequence?): FilterResults {
//                val charFiltro = p0.toString()
//                if (charFiltro.isEmpty()) {
//                    listaCopia = listaInicial
//                } else {
//                    val resultList = ArrayList<Resultado.Data>()
//                    for (personaje in listaInicial) {
//                        val nombre = personaje.name.lowercase()
//                        if (nombre.contains(charFiltro)) {
//                            resultList.add(personaje)
//                        }
//                    }
//                    listaCopia = resultList
//                }
//
//                val filterResults = FilterResults()
//                filterResults.values = listaCopia
//                return filterResults
//            }
//
//            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
//                listaCopia = p1?.values as ArrayList<Resultado.Data>
//                notifyDataSetChanged()
//            }
//
//        }
//    }
}