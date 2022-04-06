package com.estech.jsonsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.estech.jsonsample.databinding.PersonajeFragmentBinding
import com.estech.jsonsample.modelos.Personaje


/**
 * Created by sergi on 05/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class FragmentPersonaje : Fragment() {

    private lateinit var binding: PersonajeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PersonajeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val personaje: Personaje? = arguments?.getParcelable("personaje")
        if (personaje != null) {
            (requireActivity() as MainActivity).supportActionBar?.title = personaje.name
            binding.tvName.text = personaje.name
            Glide.with(this).load(personaje.image).into(binding.ivImage)
        }
    }
}