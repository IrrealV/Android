package com.estech.retrofitsample.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.estech.retrofitsample.ui.activities.MainActivity
import com.estech.retrofitsample.databinding.PersonajeFragmentBinding
import com.estech.retrofitsample.viewmodels.PersonajeViewModel


/**
 * Created by sergi on 05/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class FragmentPersonaje : Fragment() {

    private lateinit var binding: PersonajeFragmentBinding
    private val personajeViewModel: PersonajeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PersonajeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personajeViewModel.personajeSeleccionado.observe(viewLifecycleOwner) {
            (requireActivity() as MainActivity).supportActionBar?.title = it.name
            binding.tvName.text = it.name
            Glide.with(this).load(it.image).into(binding.ivImage)
        }

    }
}