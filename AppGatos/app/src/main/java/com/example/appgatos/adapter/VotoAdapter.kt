package com.example.appgatos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appgatos.R
import com.example.appgatos.databinding.VistaVotoBinding
import com.example.appgatos.dataclass.EnvioVoto
import com.example.appgatos.dataclass.Voto
import com.example.appgatos.retrofit.Repositorio
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class VotoAdapter(var listVotos : List<Voto>):
    RecyclerView.Adapter<VotoAdapter.CeldaVoto>() {
        inner class CeldaVoto(val binding: VistaVotoBinding): RecyclerView.ViewHolder(binding.root)

    private lateinit var listavotos : EnvioVoto

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VotoAdapter.CeldaVoto {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = VistaVotoBinding.inflate(layoutInflater,parent,false)
        return  CeldaVoto(binding)
    }

    override fun onBindViewHolder(holder: VotoAdapter.CeldaVoto, position: Int) {
        val hol = holder.binding
        val voto: Voto = listVotos[position]
        val repo = Repositorio()

        creacion(holder,position)
        
        hol.delbtn.setOnClickListener{

//            CoroutineScope(Dispatchers.IO).launch {
//                val gatos = repo.todosLosGatos()
//
//                withContext(Dispatchers.Main){
//                    if(gatos.isSuccessful){
//                        val listGatos = gatos.body()
//                        listGatos?.let { configRecycler(listGatos) }
//                    }
//                }
//
//            }

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