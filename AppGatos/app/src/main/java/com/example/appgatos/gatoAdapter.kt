package com.example.appgatos


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appgatos.databinding.VistaGatoBinding
import com.example.appgatos.dataclass.Gato

class gatoAdapter (val gato: List<Gato>) :
    RecyclerView.Adapter<gatoAdapter.MiCelda>() {
        inner class MiCelda(val binding: VistaGatoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = VistaGatoBinding.inflate(layoutInflater,parent,false)
        return  MiCelda(binding)
    }
    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val gato: Gato = gato.get(position)

        creacion(holder,position)

        holder.itemView.setOnClickListener {
            val bundle = bundleOf("gato" to gato)
            val navigation = holder.itemView.findNavController()
            navigation.navigate(R.id.action_fragmentLista_to_fragmentGato, bundle)
        }
    }

    override fun getItemCount(): Int {
        return gato.size
    }

    private fun creacion(holder: MiCelda, Int: Int) {
        val gato: Gato = gato.get(Int)
        holder.binding.GatoTxt.text = gato.name
        holder.binding.LugarTxt.text = gato.origin
        Glide.with(holder.itemView).load(gato.image?.url).into(holder.binding.GatoImg)

    }

}