package com.estech.jsonsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.estech.retrofitsample.PersonajeAdapter
import com.estech.retrofitsample.databinding.ListaFragmentBinding
import com.estech.retrofitsample.modelos.Personaje
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream


/**
 * Created by sergi on 05/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class FragmentLista : Fragment() {

    private lateinit var binding: ListaFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListaFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsonString = getJsonFromAsset()

        jsonString.let {
            val gson = Gson()
            val plantilla = gson.fromJson(it, Array<Personaje>::class.java).asList()
            configRecycler(plantilla)
        }
    }

    private fun getJsonFromAsset(): String? {
        var jsonString: String? = null
        try {
            jsonString = requireContext().assets.open("personajes.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
        return jsonString
    }

    private fun getJsonFromAsset2(): String? {
        var jsonString: String? = null
        try {
            val inputStream: InputStream = requireContext().assets.open("personajes.json")
            jsonString = inputStream.bufferedReader().use {
                it.readText()
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
        return jsonString
    }

    private fun configRecycler(listaPersonajes: List<Personaje>) {
        val recyclerView = binding.recyclerview
        val adapter = PersonajeAdapter(listaPersonajes)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

}