package com.estech.recyclerviewexample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.estech.recyclerviewexample.databinding.HolderPlayerBinding
import com.estech.recyclerviewexample.model.Jugador


/**
 * Created by sergi on 02/03/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val dataList = ArrayList<Jugador>()

    inner class MyViewHolder(val binding: HolderPlayerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderPlayerBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val jugador = dataList[position]
        holder.binding.holderName.text = jugador.name
        holder.binding.holderNumber.text = jugador.dorsal.toString()
        holder.binding.holderPos.text = jugador.posicion
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun update(list: ArrayList<Jugador>) {
        dataList.clear()
        dataList.addAll(list)
        notifyItemRangeChanged(list.size - 1, list.size)

//        notifyDataSetChanged()
    }

}