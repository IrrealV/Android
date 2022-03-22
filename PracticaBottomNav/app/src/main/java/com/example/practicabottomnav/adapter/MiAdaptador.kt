package com.example.practicabottomnav.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicabottomnav.Lista
import com.example.practicabottomnav.databinding.HolderListaBinding

class MiAdaptador(val listado: MutableList<Lista>): RecyclerView.Adapter<tarjetas>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tarjetas {
        val creador = LayoutInflater.from(parent.context)
        val vision = HolderListaBinding.inflate(creador, parent, false)
        val tarj = tarjetas(vision)
        return tarj

    }

    override fun onBindViewHolder(holder: tarjetas, position: Int) {
        val leyenda = listado[position]
        holder.binding.name.text = leyenda.nombre
        holder.binding.habilidad.text = leyenda.habilidad
        holder.binding.edad.text = leyenda.edad.toString()

        holder.binding.card.setOnLongClickListener {
            deleteItem(position)
            true
        }

    }

    private fun deleteItem(position: Int) {
        if (position < listado.size){
            listado.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, listado.size)
        }
    }


    override fun getItemCount(): Int {
        return listado.size
    }
}