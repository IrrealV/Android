package com.estech.gatosmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.estech.gatosmvvm.R
import com.estech.gatosmvvm.databinding.VoteListRowBinding
import com.estech.gatosmvvm.modelos.listavotos.Votes


/**
 * Created by sergi on 11/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class VotesAdapter(val listener: OnItemClickListener) :
    RecyclerView.Adapter<VotesAdapter.CeldaHolder>() {

    private val listaVotos = ArrayList<Votes>()

    inner class CeldaHolder(val binding: VoteListRowBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(vote: Votes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CeldaHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VoteListRowBinding.inflate(inflater, parent, false)
        return CeldaHolder(binding)
    }

    override fun onBindViewHolder(holder: CeldaHolder, position: Int) {
        val voto: Votes = listaVotos.get(position)

        if (voto.imageId != null) {
            Glide.with(holder.itemView).load(
                "https://cdn2.thecatapi.com/images/" + voto.imageId + ".jpg"
            )
                .placeholder(R.drawable.logo)
                .into(holder.binding.thumbnail)

        }

        val logoMeGusta = if (voto.value == 1)
            R.drawable.ic_baseline_thumb_up_24
        else
            R.drawable.ic_baseline_thumb_down_off_alt_24
        holder.binding.like.setImageResource(logoMeGusta)

        holder.binding.origin.text = voto.createdAt
        holder.binding.deleteButton.setOnClickListener {
            listener.onItemClick(voto)
        }
    }

    override fun getItemCount(): Int {
        return listaVotos.size
    }

    fun actualizaLista(lista: ArrayList<Votes>) {
        listaVotos.clear()
        listaVotos.addAll(lista)
        notifyDataSetChanged()
    }

}