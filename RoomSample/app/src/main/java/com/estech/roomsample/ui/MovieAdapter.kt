package com.estech.roomsample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.estech.roomsample.databinding.CeldaBinding
import com.estech.roomsample.room.MovieEntity


/**
 * Created by sergi on 10/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    var lista: ArrayList<MovieEntity> = ArrayList()

    inner class MovieHolder(val binding: CeldaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CeldaBinding.inflate(layoutInflater, parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = lista[position]
        holder.binding.tvTitle.text = movie.title
        holder.binding.tvYear.text = movie.year.toString()
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun updateUserList(arrayList: ArrayList<MovieEntity>) {
        lista.clear()
        lista = arrayList
        notifyDataSetChanged()
    }
}