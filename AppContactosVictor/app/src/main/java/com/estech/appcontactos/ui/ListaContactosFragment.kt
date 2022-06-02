package com.estech.appcontactos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.estech.appcontactos.MyApp
import com.estech.appcontactos.databinding.FragmentListaContactosBinding
import com.estech.appcontactos.domain.models.Contacto
import com.estech.appcontactos.domain.room.ContactosDao
import com.estech.appcontactos.domain.room.Repositorio
import com.estech.appcontactos.ui.adapter.ListaContactosAdapter
import com.estech.appcontactos.viewmodel.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Created by sergi on 13/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class ListaContactosFragment: Fragment() {

    private lateinit var binding: FragmentListaContactosBinding
    private lateinit var adapter: ListaContactosAdapter
    private lateinit var contacto: ArrayList<Contacto>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListaContactosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contacto = ArrayList()

        val myApp = requireActivity().application as MyApp
        val vm: MyViewModel by activityViewModels{
            MyViewModel.MyViewModelFactory(myApp.repositor)
        }
        binding.fab.setOnClickListener {
            vm.insertarContacto(
                Contacto(
                "Luis",
                "Heras",
                "",
                "",
                20,
                true
            )
            )
        }



        configRecicler()

        vm.todosContactos.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
    }

    private fun configRecicler(){
        adapter = ListaContactosAdapter()
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter
    }



}