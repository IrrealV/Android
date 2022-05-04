package com.example.appgatos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appgatos.R
import com.example.appgatos.databinding.VistaVotoBinding
import com.example.appgatos.dataclass.Voto

class VotoAdapter(val listVotos : List<Voto>):
    RecyclerView.Adapter<VotoAdapter.CeldaVoto>() {
        inner class CeldaVoto(val binding: VistaVotoBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VotoAdapter.CeldaVoto {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = VistaVotoBinding.inflate(layoutInflater,parent,false)
        return  CeldaVoto(binding)
    }

    override fun onBindViewHolder(holder: VotoAdapter.CeldaVoto, position: Int) {

        creacion(holder,position)

        holder.itemView.setOnClickListener {
            val navigation = holder.itemView.findNavController()
            navigation.navigate(R.id.action_fragmentLista_to_fragmentGato)
        }
    }

    override fun getItemCount(): Int {
        return listVotos.size
    }

    private fun creacion(holder: VotoAdapter.CeldaVoto, Int: Int) {
        val voto: Voto = listVotos[Int]
        val hol = holder.binding
        Glide.with(holder.itemView).load(voto.imageId).into(hol.gatoImg)
        hol.fechaTxt.text = voto.createdAt

        when(voto.value){
            0 -> hol.votacion.setImageResource(R.drawable.ic_baseline_thumb_down_24)
            1 -> hol.votacion.setImageResource(R.drawable.ic_baseline_thumb_up_24)
        }

    }
}