package com.estech.coordinatorlayoutsample.collapsingconfab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.estech.coordinatorlayoutsample.Cheeses
import com.estech.coordinatorlayoutsample.adapter.CheeseAdapter
import com.estech.coordinatorlayoutsample.databinding.CollapsingFabBinding
import com.estech.coordinatorlayoutsample.databinding.ToolbarEnteralwaysBinding


/**
 * Created by sergi on 26/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class CollapseYFab : AppCompatActivity() {

    private lateinit var binding: CollapsingFabBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CollapsingFabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        setSupportActionBar(binding.toolbar)
        title = "Floating Action Button over Collapsing Toolbar"
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerview
        val listaQuesos = Cheeses.getRandomSublist(30)

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = CheeseAdapter(listaQuesos)
    }
}