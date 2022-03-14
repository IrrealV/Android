package com.estech.recyclerviewexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estech.recyclerviewexample.adapter.MyAdapter
import com.estech.recyclerviewexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jugador1 = Jugador("Casillas", 1, "Portero")
        val jugador2 = Jugador("Casillas", 1, "Portero")
        val jugador3 = Jugador("Casillas", 1, "Portero")
        val jugador4 = Jugador("Casillas", 1, "Portero")
        val seleccion = mutableListOf(jugador1, jugador2, jugador3, jugador4)

//        val jugadores = mutableListOf("Casillas", "Iniesta", "Villa", "Busquets", "Fernando Torres")
//        jugadores.add("Xavi")
//        jugadores.add("Capdevila")
//        jugadores.add("Jesús Navas")
//        jugadores.add("David Silva")

        val recyclerView = binding.recyclerview
        val llm = LinearLayoutManager(this)
        recyclerView.layoutManager = llm
        val adapter = MyAdapter(seleccion)
        recyclerView.adapter = adapter

        binding.button.setOnClickListener {
            adapter.aniadirJugador(Jugador("SErgio Ramos", 15, "Defensa"))
        }

        binding.button2.setOnClickListener {
            adapter.removeItem(0)
        }
    }
}