package com.estech.viewpagersample.galeriafragments.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.estech.viewpagersample.databinding.Tutorial2Binding


/**
 * Created by sergi on 22/03/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class Tutorial2 : Fragment() {

    private lateinit var binding: Tutorial2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Tutorial2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val preferences = requireActivity().getSharedPreferences("mispref", Context.MODE_PRIVATE)

        val espania = preferences.getBoolean("espania", false)
        binding.switch1.isChecked = espania

        val boletin = preferences.getBoolean("boletin", false)
        binding.switch2.isChecked = boletin

        val editor = preferences.edit()
        binding.switch1.setOnCheckedChangeListener { compoundButton, boolean ->
            editor.putBoolean("espania", boolean )
            editor.apply()
        }

        binding.switch2.setOnCheckedChangeListener { compoundButton, boolean ->
            editor.putBoolean("boletin", boolean)
            editor.apply()
        }
    }
}