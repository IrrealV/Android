package com.estech.viewpagersample.galeriafragments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.estech.viewpagersample.databinding.Tutorial3Binding


/**
 * Created by sergi on 22/03/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class Tutorial3 : Fragment() {

    private lateinit var binding: Tutorial3Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Tutorial3Binding.inflate(inflater, container, false)
        return binding.root
    }

}