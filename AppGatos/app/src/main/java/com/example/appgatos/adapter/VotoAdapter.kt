package com.example.appgatos.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appgatos.R
import com.example.appgatos.databinding.VistaVotoBinding
import com.example.appgatos.dataclass.Voto
import com.example.appgatos.retrofit.Repositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VotoAdapter(var listVotos : ArrayList<Voto>, val context: Context):
    RecyclerView.Adapter<VotoAdapter.CeldaVoto>() {
        inner class CeldaVoto(val binding: VistaVotoBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VotoAdapter.CeldaVoto {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = VistaVotoBinding.inflate(layoutInflater,parent,false)
        return  CeldaVoto(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: VotoAdapter.CeldaVoto, position: Int) {
        val hol = holder.binding
        val voto : Voto = listVotos[position]


        creacion(holder,position)
        
        hol.delbtn.setOnClickListener{
            val repo = Repositorio()

            CoroutineScope(Dispatchers.IO).launch {
                val delmichi = repo.borrarVoto(voto.id)

                withContext(Dispatchers.Main){
                    if(delmichi.isSuccessful){
                        Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                        listVotos.removeAt(position)
                        notifyItemRemoved(position)
                    }
                    else{
                        val error = delmichi.message()
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }



    }

    override fun getItemCount(): Int {
        return listVotos.size
    }



    private fun creacion(holder: VotoAdapter.CeldaVoto, Int: Int) {
        val voto: Voto = listVotos[Int]
        val hol = holder.binding
        Glide.with(holder.itemView).load("https://cdn2.thecatapi.com/images/${voto.imageId}.jpg").into(hol.gatoImg)
        hol.fechaTxt.text = voto.createdAt

        when(voto.value){
            0 -> hol.votacion.setImageResource(R.drawable.ic_baseline_thumb_down_24)
            1 -> hol.votacion.setImageResource(R.drawable.ic_baseline_thumb_up_24)
        }

    }
}