package com.estech.recyclerviewexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.estech.recyclerviewexample.databinding.HolderPlayerBinding


/**
 * Created by sergi on 02/03/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class MyAdapter(val dataList: MutableList<String>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.holder_name)
        }

        fun setMyData(jugador: String) {
            textView.text = jugador
            textView.setOnClickListener {
                Toast.makeText(textView.context, "$jugador en posicion ${adapterPosition}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.holder_player, parent, false)
        return MyViewHolder(view)
    }


//    inner class MyViewHolder(private val binding: HolderPlayerBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun setMyData(jugador: String) {
//            binding.holderName.text = jugador
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = HolderPlayerBinding.inflate(layoutInflater, parent, false)
//        return MyViewHolder(binding)
//    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val jugador = dataList[position]
        holder.setMyData(jugador)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}