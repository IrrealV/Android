package com.estech.coordinatorlayoutsample.toolbarsola

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.estech.coordinatorlayoutsample.Cheeses
import com.estech.coordinatorlayoutsample.adapter.CheeseAdapter
import com.estech.coordinatorlayoutsample.databinding.ToolbarEnteralwaysBinding
import com.estech.coordinatorlayoutsample.databinding.ToolbarScrollBinding


/**
 * Created by sergi on 26/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class ToolbarEnterAlways: AppCompatActivity() {

    private lateinit var binding: ToolbarEnteralwaysBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ToolbarEnteralwaysBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        setSupportActionBar(binding.toolbar)
        title = "Toolbar enter always"
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.recyclerview
        val listaQuesos = Cheeses.getRandomSublist(30)

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = CheeseAdapter(listaQuesos)
    }
}