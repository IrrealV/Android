package com.estech.gatosmvvm.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.estech.gatosmvvm.R
import com.estech.gatosmvvm.databinding.ItemBreedBinding
import com.estech.gatosmvvm.modelos.listagatos.Breed
import java.lang.String
import kotlin.Boolean
import kotlin.CharSequence
import kotlin.Int
import kotlin.to
import kotlin.toString


/**
 * Created by sergi on 11/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class BreedsAdapter(val listener: RazaClickListener) : RecyclerView.Adapter<BreedsAdapter.CeldaHolder>(), Filterable {

    private val listaRazas = ArrayList<Breed>()
    private var listaCopia = ArrayList<Breed>()
    interface RazaClickListener {
        fun OnClick(breed: Breed)
    }

    inner class CeldaHolder(val binding: ItemBreedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CeldaHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBreedBinding.inflate(inflater, parent, false)
        return CeldaHolder(binding)
    }

    override fun onBindViewHolder(holder: CeldaHolder, position: Int) {
        val breed: Breed = listaCopia.get(position)
        holder.binding.tvTitle.text = breed.name
        holder.binding.inteli.text = String.valueOf(breed.origin)
        holder.itemView.setOnClickListener {
            listener.OnClick(breed)
        }
    }

    override fun getItemCount(): Int {
        return listaCopia.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun actualizaLista(lista: ArrayList<Breed>) {
        listaRazas.clear()
        listaRazas.addAll(lista)
        listaCopia.clear()
        listaCopia.addAll(lista)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charFiltro = p0.toString()
                if (charFiltro.isEmpty()) {
                    listaCopia = listaRazas
                } else {
                    listaCopia = listaRazas.filter {
                        it.name!!.lowercase().contains(charFiltro.lowercase()) ||
                                it.origin!!.lowercase().contains(charFiltro.lowercase())
                    } as ArrayList<Breed>

                }

                val filterResults = FilterResults()
                filterResults.values = listaCopia
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                listaCopia = p1?.values as ArrayList<Breed>
                notifyDataSetChanged()
            }

        }
    }

    fun cambiarOrden(reverse: Boolean) {
        if (reverse) {
            listaCopia.sortByDescending { it.name }
            listaRazas.sortByDescending { it.name }
        } else {
            listaCopia.sortBy { it.name }
            listaRazas.sortBy { it.name }
        }
        notifyDataSetChanged()
    }

}