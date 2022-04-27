package com.estech.coordinatorlayoutsample.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.estech.coordinatorlayoutsample.Cheeses
import com.estech.coordinatorlayoutsample.adapter.CheeseAdapter
import com.estech.coordinatorlayoutsample.databinding.TabLayoutFragmentsBinding


/**
 * Created by sergi on 26/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class Tab3Fragment: Fragment() {

    private lateinit var binding: TabLayoutFragmentsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TabLayoutFragmentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerview
        val listaQuesos = Cheeses.getRandomSublist(30)

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = CheeseAdapter(listaQuesos)
    }
}