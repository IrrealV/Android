package com.example.recicler_personalizado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recicler_personalizado.adapter.MiAdaptador
import com.example.recicler_personalizado.adapter.tarjetas
import com.example.recicler_personalizado.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView =binding.recyclerView

        val leyendas = mutableListOf<Leyenda>()
        leyendas.add(Leyenda("Bangalore",34,"Lanzahumo"))
        leyendas.add(Leyenda("Gibraltar",34,"Lanzahumo"))
        leyendas.add(Leyenda("Bangalore",34,"Lanzahumo"))
        leyendas.add(Leyenda("Bangalore",34,"Lanzahumo"))
        leyendas.add(Leyenda("Bangalore",34,"Lanzahumo"))
        leyendas.add(Leyenda("Bangalore",34,"Lanzahumo"))



        val llm = LinearLayoutManager(this)
        recyclerView.layoutManager = llm

        val adapter = MiAdaptador(leyendas)
        recyclerView.adapter = adapter



    }
}