package com.estech.examen2trimestre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.estech.examen2trimestre.databinding.VistaCeldaBinding


/**
 * Created by sergi on 28/03/2022.
 */

class MyAdapter(val listado: MutableList<Mensaje>) : RecyclerView.Adapter<MyAdapter.MiCelda>() {

    // clase para la celda
    inner class MiCelda(val binding: VistaCeldaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = VistaCeldaBinding.inflate(layoutInflater, parent, false)
        val celda = MiCelda(binding)
        return celda
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val mensaje = listado[position]
        holder.binding.tvMessage.text = mensaje.mensaje
        holder.binding.tvDate.text = mensaje.fecha

        holder.binding.cardview.setOnLongClickListener {
            eliminarTarea(position)
            true
        }
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    fun aniadirTarea(mensaje: Mensaje) {
        listado.add(mensaje)
        notifyDataSetChanged()
    }

    fun eliminarTarea(position: Int) {
        listado.removeAt(position)
        notifyDataSetChanged()
    }
}