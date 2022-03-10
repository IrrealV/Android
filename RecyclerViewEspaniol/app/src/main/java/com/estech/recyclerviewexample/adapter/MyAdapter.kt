package com.estech.recyclerviewexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.estech.recyclerviewexample.Jugador
import com.estech.recyclerviewexample.databinding.LayoutDelJugadorBinding

class MyAdapter(val listado: MutableList<Jugador>): RecyclerView.Adapter<CeldaEnCadaPosicion>() {


    override fun onCreateViewHolder(vistaPadre: ViewGroup, tipoDeVista: Int): CeldaEnCadaPosicion {
        val dibujante = LayoutInflater.from(vistaPadre.context)   // FUNCIÓN QUE PERMITE INFLAR LAYOUT
        val vista = LayoutDelJugadorBinding.inflate(dibujante, vistaPadre, false)
        val celda = CeldaEnCadaPosicion(vista)
        return celda
    }

    override fun onBindViewHolder(celdaActual: CeldaEnCadaPosicion, position: Int) {
        val jugador = listado[position]
        val espania = "España"
//        holder.setMyData(jugador)
        /////^^*^******ALTERNATIVA*********************////////
        celdaActual.binding.textviewNombre.text = jugador.nombre
        celdaActual.binding.textviewPosicion.text = jugador.posicion
    }

    override fun getItemCount() = listado.size

//    override fun getItemCount(): Int {
//       return listado.size
//    }
    fun aniadirJugador(jugador: Jugador) {
        listado.add(jugador)
        notifyDataSetChanged()
    }
}