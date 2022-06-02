package com.example.appciudades.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appciudades.MyBeer
import com.example.appciudades.databinding.CervezaCeldaBinding
import com.example.appciudades.dominio.models.Cerveza
import com.example.appciudades.viewModel.MyVM

class ListaBeerAdapter:RecyclerView.Adapter<ListaBeerAdapter.CerverzaCelda>() {
    inner class CerverzaCelda(val binding: CervezaCeldaBinding):RecyclerView.ViewHolder(binding.root)

    private val listaBeer = ArrayList<Cerveza>()
    private val miapp = MyBeer()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CerverzaCelda {
        val inflater = LayoutInflater.from(parent.context)
        val  binding = CervezaCeldaBinding.inflate(inflater,parent,false)
        return CerverzaCelda(binding)
    }

    override fun onBindViewHolder(holder: CerverzaCelda, position: Int) {
        val bind = holder.binding
        val cerveza = listaBeer[position]

        bind.name.text = cerveza.nombre
        bind.Pais.text = cerveza.ciudad
        bind.location.text = cerveza.Ubi
        //bind.CerImg.setImageResource(cerveza.img) preguntar mañana en clase


        val vm= MyVM(miapp.repositorio)



    }

    override fun getItemCount(): Int {
        return listaBeer.size
    }

    fun updateList(lista:List<Cerveza>){
        listaBeer.clear()
        listaBeer.addAll(lista)
        notifyItemRangeChanged(lista.size -1, lista.size)
    }

}