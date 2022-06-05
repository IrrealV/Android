package com.example.appciudades.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.appciudades.MyBeer
import com.example.appciudades.databinding.CervezaCeldaBinding
import com.example.appciudades.dominio.models.Cerveza
import com.example.appciudades.viewModel.MyVM

class ListaBeerAdapter(val context: Context):RecyclerView.Adapter<ListaBeerAdapter.CerverzaCelda>() {
    inner class CerverzaCelda(val binding: CervezaCeldaBinding):RecyclerView.ViewHolder(binding.root)

    private val listaBeer = ArrayList<Cerveza>()
    private val miapp = MyBeer()
    private var posicion= 0



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CerverzaCelda {
        val inflater = LayoutInflater.from(parent.context)
        val  binding = CervezaCeldaBinding.inflate(inflater,parent,false)
        return CerverzaCelda(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CerverzaCelda, position: Int) {
        posicion = holder.adapterPosition
        val bind = holder.binding
        val cerveza = listaBeer[position]
        val vm= MyVM(miapp.repositorio)
        bind.name.text = cerveza.nombre
        bind.Pais.text = cerveza.ciudad
        bind.lat.text = "${cerveza.latitud},"
        bind.lon.text = cerveza.longitud.toString()
        //bind.CerImg.setImageResource(cerveza.img) preguntar mañana en clase



        bind.delete.setOnClickListener {
            verificacion(vm,cerveza, position)
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

    private fun verificacion(vm:MyVM, cerveza: Cerveza,position: Int){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("¿Estás seguro de borrar ${cerveza.nombre}?")
        builder.setPositiveButton("Si") { dialog, which ->
            vm.eliminarCerveza(cerveza)
            notifyItemRemoved(position)
        }
        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(context, "Pues nada", Toast.LENGTH_SHORT).show()
        }
        val dialog = builder.create()
        dialog.show()
    }

}