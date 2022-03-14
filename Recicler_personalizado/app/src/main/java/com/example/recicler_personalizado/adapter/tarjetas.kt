package com.example.recicler_personalizado.adapter

import android.media.metrics.Event
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.recicler_personalizado.databinding.HolderLeyendaBinding

class tarjetas(val binding: HolderLeyendaBinding): RecyclerView.ViewHolder(binding.root){
    fun updateTarjeta(event : Event){
            binding.borrar.setOnClickListener {
                deleteEvent(event)
            }
    }

    private fun deleteEvent(event: Event) {
        ViewModel.removeItem()
    }


}