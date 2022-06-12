package com.example.appciudades.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.appciudades.MyBeer
import com.example.appciudades.R
import com.example.appciudades.databinding.CervezaCeldaBinding
import com.example.appciudades.dominio.models.Cerveza
import com.example.appciudades.viewModel.MyVM


class ListaBeerAdapter(val context: Context, listaBeer: ArrayList<Cerveza>)
    : RecyclerView.Adapter<ListaBeerAdapter.CerverzaCelda>() {

    inner class CerverzaCelda(val binding: CervezaCeldaBinding):RecyclerView.ViewHolder(binding.root)

    private val copiaBeer = listaBeer
    private val miapp = MyBeer()
    private var posicion= 0
    private var listaCopia = listaBeer



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CerverzaCelda {
        val inflater = LayoutInflater.from(parent.context)
        val  binding = CervezaCeldaBinding.inflate(inflater,parent,false)
        return CerverzaCelda(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CerverzaCelda, position: Int) {

        posicion = holder.adapterPosition
        val bind = holder.binding
        val cerveza = listaCopia[position]

        val vm= MyVM(miapp.repositorio)

        bind.CerImg.setImageURI(cerveza.img?.toUri())
        bind.name.text = cerveza.nombre
        bind.Pais.text = cerveza.ciudad
        bind.lat.text = "${cerveza.latitud},"
        bind.lon.text = cerveza.longitud.toString()


        bind.celdaCont.setOnClickListener {
            val cerv  = copiaBeer[position].id
            val navigation = holder.itemView.findNavController()
            val bundle = bundleOf("cerveza" to cerv)
            navigation.navigate(R.id.action_listaFragment_to_aboutBeerFragment,bundle)
        }


        probador(holder,listaCopia,position)


        bind.delete.setOnClickListener {
            verificacion(vm,cerveza, position)
        }


    }

    override fun getItemCount(): Int {
        return listaCopia.size
    }

    fun updateList(lista:List<Cerveza>){
        listaCopia.clear()
        listaCopia.addAll(lista)
        notifyItemRemoved(posicion)
        notifyItemRangeChanged(listaCopia.size,listaCopia.size-1)

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

    private fun probador(holder: CerverzaCelda, lista: List<Cerveza>, position: Int){
        val bind = holder.binding
        when(lista[position].probado){
            true ->{
                bind.likeNolike.setBackgroundResource(R.drawable.megusta_icono)
                bind.likeNolike.background.setTint(ContextCompat.getColor(context, R.color.teal_200))

            }
            false -> {
                bind.likeNolike.setBackgroundResource(R.drawable.ic_baseline_sentiment_dissatisfied_24)
                bind.likeNolike.background.setTint(ContextCompat.getColor(context, R.color.red))}
        }
    }

}