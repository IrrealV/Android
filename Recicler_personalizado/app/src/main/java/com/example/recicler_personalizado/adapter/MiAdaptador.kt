package com.example.recicler_personalizado.adapter



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.recicler_personalizado.Leyenda
import com.example.recicler_personalizado.databinding.HolderLeyendaBinding



class MiAdaptador(val listado: MutableList<Leyenda>): RecyclerView.Adapter<tarjetas>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tarjetas {
        val creador =LayoutInflater.from(parent.context)
        val vision = HolderLeyendaBinding.inflate(creador, parent, false)
        val tarj = tarjetas(vision)
        return tarj

    }

    override fun onBindViewHolder(holder: tarjetas, position: Int) {
        val leyenda = listado[position]
        holder.binding.name.text = leyenda.nombre
        holder.binding.habilidad.text = leyenda.habilidad
        holder.binding.edad.text = leyenda.edad.toString()


    }



    override fun getItemCount(): Int {
        return listado.size
    }


}