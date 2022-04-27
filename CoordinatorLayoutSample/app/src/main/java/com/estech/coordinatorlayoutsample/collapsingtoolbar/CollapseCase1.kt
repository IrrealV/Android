package com.estech.coordinatorlayoutsample.collapsingtoolbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.estech.coordinatorlayoutsample.Cheeses
import com.estech.coordinatorlayoutsample.adapter.CheeseAdapter
import com.estech.coordinatorlayoutsample.databinding.CollapseCase1Binding

class CollapseCase1 : AppCompatActivity() {
    private lateinit var binding: CollapseCase1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CollapseCase1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        binding.toolbar.title = "Collapsing scroll y enterAlways"
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerview
        val listaQuesos = Cheeses.getRandomSublist(30)

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = CheeseAdapter(listaQuesos)
    }
}