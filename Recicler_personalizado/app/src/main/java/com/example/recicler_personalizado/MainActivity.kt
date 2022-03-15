package com.example.recicler_personalizado

import android.R.menu
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recicler_personalizado.adapter.MiAdaptador
import com.example.recicler_personalizado.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val llm = LinearLayoutManager(this)
    val leyendas = mutableListOf<Leyenda>()
    val adapter = MiAdaptador(leyendas)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView =binding.recyclerView

        leyendas.add(Leyenda("Bangalore",34,"Lanzahumo"))
        leyendas.add(Leyenda("Gibraltar",34,"Lanzahumo"))
        leyendas.add(Leyenda("Bangalore",34,"Lanzahumo"))
        leyendas.add(Leyenda("Bangalore",34,"Lanzahumo"))
        leyendas.add(Leyenda("Bangalore",34,"Lanzahumo"))
        leyendas.add(Leyenda("Bangalore",34,"Lanzahumo"))


        recyclerView.layoutManager = llm


        recyclerView.adapter = adapter



    }


    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mimenu, menu)
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun borrartodo() {
        leyendas.removeAll(leyendas)
        adapter.notifyDataSetChanged()
        return

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.restall -> {
                borrartodo()
                true
            }
            else -> {
                false
            }
        }

    }

}