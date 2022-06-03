package com.example.appciudades.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appciudades.MyBeer
import com.example.appciudades.databinding.CervezaCeldaBinding
import com.example.appciudades.dominio.models.Cerveza
import com.example.appciudades.viewModel.MyVM
import kotlin.properties.Delegates

class ListaBeerAdapter():RecyclerView.Adapter<ListaBeerAdapter.CerverzaCelda>() {
    inner class CerverzaCelda(val binding: CervezaCeldaBinding):RecyclerView.ViewHolder(binding.root)

    private val listaBeer = ArrayList<Cerveza>()
    private val miapp = MyBeer()
    private var posicion= 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CerverzaCelda {
        val inflater = LayoutInflater.from(parent.context)
        val  binding = CervezaCeldaBinding.inflate(inflater,parent,false)
        return CerverzaCelda(binding)
    }

    override fun onBindViewHolder(holder: CerverzaCelda, position: Int) {
        posicion = holder.adapterPosition
        val bind = holder.binding
        val cerveza = listaBeer[position]

        bind.name.text = cerveza.nombre
        bind.Pais.text = cerveza.ciudad
        bind.location.text = "${cerveza.latitud},${cerveza.Longitud}"
        //bind.CerImg.setImageResource(cerveza.img) preguntar mañana en clase


        val vm= MyVM(miapp.repositorio)

        bind.delete.setOnClickListener {
            vm.eliminarCerveza(cerveza)
            notifyItemRemoved(position)
        }


    }

    override fun getItemCount(): Int {
        return listaBeer.size
    }

    fun updateList(lista:List<Cerveza>){
        listaBeer.clear()
        listaBeer.addAll(lista)
        notifyItemRemoved(posicion)
        notifyItemRangeChanged(listaBeer.size,listaBeer.size-1)

    }

}