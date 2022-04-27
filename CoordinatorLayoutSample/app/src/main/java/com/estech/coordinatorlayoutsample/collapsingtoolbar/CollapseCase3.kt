package com.estech.coordinatorlayoutsample.collapsingtoolbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.estech.coordinatorlayoutsample.Cheeses
import com.estech.coordinatorlayoutsample.adapter.CheeseAdapter
import com.estech.coordinatorlayoutsample.databinding.CollapseCase3Binding

class CollapseCase3 : AppCompatActivity() {
    private lateinit var binding: CollapseCase3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CollapseCase3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        binding.toolbar.title = "Collapsing scroll, enterAlways y enterAlways collapsed"
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerview
        val listaQuesos = Cheeses.getRandomSublist(30)

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = CheeseAdapter(listaQuesos)
    }
}