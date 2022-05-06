package com.example.appgatos.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appgatos.R
import com.example.appgatos.databinding.VistaGatoBinding
import com.example.appgatos.dataclass.Gato


class GatoAdapter (val listaGatos: ArrayList<Gato>) :
    RecyclerView.Adapter<GatoAdapter.MiCelda>(), Filterable {

    private var listaCopia = listaGatos

        inner class MiCelda(val binding: VistaGatoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = VistaGatoBinding.inflate(layoutInflater,parent,false)
        return  MiCelda(binding)
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val gato: Gato = listaCopia.get(position)

        creacion(holder,position)

        holder.itemView.setOnClickListener {
            val bundle = bundleOf("gato" to gato)
            val navigation = holder.itemView.findNavController()
            navigation.navigate(R.id.action_fragmentLista_to_fragmentGato, bundle)
        }

    }

    override fun getItemCount(): Int {
        return listaCopia.size
    }

    private fun creacion(holder: MiCelda, Int: Int) {
        val gato: Gato? = listaCopia[Int]
        holder.binding.GatoTxt.text = gato?.name
        holder.binding.LugarTxt.text = gato?.origin
        Glide.with(holder.itemView).load(gato?.image?.url).into(holder.binding.GatoImg)

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val palabraABuscar = p0.toString()

                if (palabraABuscar.isEmpty()) {
                    listaCopia = listaGatos
                } else {
                    listaCopia = listaGatos.filter {
                        (it.name?.lowercase()!!.contains(palabraABuscar.lowercase()) ||
                                it.origin.toString().lowercase().contains(palabraABuscar.lowercase()))
                    } as ArrayList<Gato>
                }
                val filterResults = FilterResults()
                filterResults.values = listaCopia
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                listaCopia = p1?.values as ArrayList<Gato>
                notifyDataSetChanged()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(listaPersonajes: ArrayList<Gato>) {
        listaCopia = listaPersonajes
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun OrdHead() : Boolean{
        listaCopia.sortedByDescending { it.name }
        notifyDataSetChanged()
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    fun OrdTail() : Boolean{
        listaCopia.sortedBy{ it.name }
        notifyDataSetChanged()
        return true
    }

}

