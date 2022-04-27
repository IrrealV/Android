package com.estech.coordinatorlayoutsample.bottomappbar

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.estech.coordinatorlayoutsample.R
import com.estech.coordinatorlayoutsample.databinding.BottomappbarBinding


/**
 * Created by sergi on 26/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class BottomAppBarActivity : AppCompatActivity() {

    private lateinit var binding: BottomappbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BottomappbarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.bottomAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.floatingActionButton.setOnClickListener {
            Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_bottomappbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.app_bar_fav -> {
                Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.app_bar_search -> {
                Toast.makeText(this, "Ayuda", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.app_bar_settings -> {
                Toast.makeText(this, "Favoritos", Toast.LENGTH_SHORT).show()
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}