package com.estech.retrofitsample.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.estech.retrofitsample.R
import com.estech.retrofitsample.databinding.ListaFragmentBinding
import com.estech.retrofitsample.domain.models.Personaje
import com.estech.retrofitsample.ui.adapters.PersonajeAdapter
import com.estech.retrofitsample.viewmodels.PersonajeViewModel


/**
 * Created by sergi on 05/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class FragmentLista : Fragment() {

    private lateinit var binding: ListaFragmentBinding
    private lateinit var adapter: PersonajeAdapter
    private val personajeViewModel: PersonajeViewModel by activityViewModels()
    private var totalPaginas = 0
    private var paginaActual = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListaFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecycler()

        personajeViewModel.personajesLiveData.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }

        personajeViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        personajeViewModel.getPersonajes(1)

        personajeViewModel.totalPaginas.observe(viewLifecycleOwner) {
            totalPaginas = it
        }

        binding.paginamas.setOnClickListener {
            if (paginaActual < totalPaginas) {
                paginaActual ++
                personajeViewModel.getPersonajes(paginaActual)
            }
        }

        binding.paginamenos.setOnClickListener {
            if (paginaActual > 0) {
                paginaActual --
                personajeViewModel.getPersonajes(paginaActual)
            }
        }

    }

    private fun configRecycler() {
        val recyclerView = binding.recyclerview
        adapter = PersonajeAdapter(object : PersonajeAdapter.OnItemClickListener {
            override fun onItemClick(personaje: Personaje) {
                personajeViewModel.selectPersonaje(personaje)
                findNavController().navigate(R.id.action_fragmentLista_to_fragmentPersonaje)
            }
        })
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

}