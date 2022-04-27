package com.estech.coordinatorlayoutsample.floatingactionbutton

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estech.coordinatorlayoutsample.Cheeses
import com.estech.coordinatorlayoutsample.adapter.CheeseAdapter
import com.estech.coordinatorlayoutsample.databinding.FabYSnackbarBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*


/**
 * Created by sergi on 26/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class FabConSnackYScroll : AppCompatActivity() {

    private lateinit var binding: FabYSnackbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FabYSnackbarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerview
        setupRecyclerView(recyclerView)

        setSupportActionBar(binding.toolbar)
        title = "FAB y SnackBar"

        binding.fab.setOnClickListener { view -> // El Snackbar es parecido a un toast. Se muestra un mensaje corto en la parte inferior de la pantalla
            // y permite mostrar un botón que realiza una acción
            Snackbar.make(view, "Esto es un SnackBar", Snackbar.LENGTH_LONG)
                .setAction("Action") {
                    Toast.makeText(
                        this@FabConSnackYScroll,
                        "Se pulsó el SnackBar",
                        Toast.LENGTH_SHORT
                    ).show()
                }.show()
        }

        // listener que detecta cuando se hace scroll en un recyclerview
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    binding.fab.hide()
                } else if (dy < 0) {
                    binding.fab.show()
                }
            }
        })
    }

    /**
     * Método para configurar el recyclerview
     *
     * @param recyclerView -> recibe como parámetro el recyclerview a configurar
     */
    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val listaQuesos = Cheeses.getRandomSublist(30)

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = CheeseAdapter(listaQuesos)
    }

    /**
     * Método para obtener el listado random de quesos
     *
     * @param array  -> array con todos los quesos
     * @param amount -> cantidad de quesos a mostrar
     * @return -> devuelve un listado de quesos
     */


}