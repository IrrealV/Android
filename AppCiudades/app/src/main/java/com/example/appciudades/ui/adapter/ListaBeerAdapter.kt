package com.example.appciudades.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
<<<<<<< Updated upstream
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
=======
import androidx.core.content.ContextCompat
>>>>>>> Stashed changes
import androidx.recyclerview.widget.RecyclerView
import com.example.appciudades.MyBeer
import com.example.appciudades.R
import com.example.appciudades.databinding.CervezaCeldaBinding
import com.example.appciudades.dominio.models.Cerveza
import com.example.appciudades.viewModel.MyVM

<<<<<<< Updated upstream
class ListaBeerAdapter(val context: Context, val listaBeer: ArrayList<Cerveza>)
    : RecyclerView.Adapter<ListaBeerAdapter.CerverzaCelda>() {
=======
class ListaBeerAdapter(val context: Context):RecyclerView.Adapter<ListaBeerAdapter.CerverzaCelda>(){
>>>>>>> Stashed changes
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
        val cerv : Cerveza = copiaBeer.get(position)


        posicion = holder.adapterPosition
        val bind = holder.binding
<<<<<<< Updated upstream
        val cerveza = copiaBeer[position]
=======
        val cerveza = listaCopia[position]
>>>>>>> Stashed changes
        val vm= MyVM(miapp.repositorio)

        bind.name.text = cerveza.nombre
        bind.Pais.text = cerveza.ciudad
        bind.lat.text = "${cerveza.latitud},"
        bind.lon.text = cerveza.longitud.toString()
        //bind.CerImg.setImageResource(cerveza.img) preguntar mañana en clase
<<<<<<< Updated upstream

        bind.celdaCont.setOnClickListener {
            val navigation = holder.itemView.findNavController()
            val bundle = bundleOf("cerveza" to position+1)
            navigation.navigate(R.id.action_listaFragment_to_aboutBeerFragment,bundle)
        }

=======
        probador(holder,listaCopia,position)
>>>>>>> Stashed changes

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

  //Pedir filtro
}