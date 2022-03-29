package com.estech.viewpagersample.tutorialactivity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.estech.viewpagersample.databinding.TutorialActivityBinding
import com.estech.viewpagersample.galeriafragments.fragments.Tutorial1
import com.estech.viewpagersample.galeriafragments.fragments.Tutorial2
import com.estech.viewpagersample.galeriafragments.fragments.Tutorial3


/**
 * Created by sergi on 22/03/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: TutorialActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TutorialActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar2)

        val listaFragment = listOf(Tutorial1(), Tutorial2(), Tutorial3())
        val adapter = TutorialAdapter2(this, listaFragment)
        binding.viewpager.adapter = adapter

        binding.btBack.setOnClickListener {
            val position = binding.viewpager.currentItem
            if (position > 0)
                binding.viewpager.currentItem = position - 1
        }

        binding.btFinish.setOnClickListener {
            finish()
        }

        binding.btForward.setOnClickListener {
            val position = binding.viewpager.currentItem
            if (position < (listaFragment.size - 1))
                binding.viewpager.currentItem = position + 1
        }

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                title = "Paso ${position + 1}"
                val numItems = listaFragment.size - 1

                when (position) {
                    0 -> {
                        binding.btBack.visibility = View.INVISIBLE
                        binding.btFinish.visibility = View.INVISIBLE
                        binding.btForward.visibility = View.VISIBLE
                    }
                    numItems -> {
                        binding.btBack.visibility = View.VISIBLE
                        binding.btFinish.visibility = View.VISIBLE
                        binding.btForward.visibility = View.INVISIBLE
                    }
                    else -> {
                        binding.btBack.visibility = View.VISIBLE
                        binding.btFinish.visibility = View.INVISIBLE
                        binding.btForward.visibility = View.VISIBLE
                    }
                }

            }
        })
    }

    override fun onBackPressed() {
        val position: Int = binding.viewpager.currentItem
        if (position == 0) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Deseas salir de la guía de configuración?")
            builder.setNegativeButton("No", null)
            builder.setPositiveButton(
                "Sí"
            ) { dialog, which -> finish() }
            builder.create().show()
        } else if (position > 0) {
            binding.viewpager.currentItem = position - 1
        } else {
            super.onBackPressed()
        }
    }
}