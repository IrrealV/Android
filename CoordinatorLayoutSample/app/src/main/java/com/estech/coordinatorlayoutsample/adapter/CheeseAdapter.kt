package com.estech.coordinatorlayoutsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.estech.coordinatorlayoutsample.Cheeses
import com.estech.coordinatorlayoutsample.databinding.CeldaQuesoBinding


/**
 * Created by sergi on 26/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class CheeseAdapter(val listaQuesos: List<String>) :
    RecyclerView.Adapter<CheeseAdapter.CheeseHolder>() {

    inner class CheeseHolder(val binding: CeldaQuesoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheeseHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CeldaQuesoBinding.inflate(layoutInflater, parent, false)
        val celda = CheeseHolder(binding)
        return celda
    }

    override fun onBindViewHolder(holder: CheeseHolder, position: Int) {
        val queso = listaQuesos[position]
        holder.binding.text1.text = queso
        holder.binding.avatar.setImageResource(Cheeses.getRandomCheesesDrawable())
    }

    override fun getItemCount(): Int {
        return listaQuesos.size
    }


}