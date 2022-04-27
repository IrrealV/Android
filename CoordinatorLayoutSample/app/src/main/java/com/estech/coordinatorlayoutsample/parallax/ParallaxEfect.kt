package com.estech.coordinatorlayoutsample.parallax

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.estech.coordinatorlayoutsample.databinding.ParallaxBinding


/**
 * Created by sergi on 26/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class ParallaxEfect : AppCompatActivity() {

    private lateinit var binding: ParallaxBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ParallaxBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        title = "Parallax effect"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}