package com.example.recicler_personalizado

import android.R.menu
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter

        leyendas.add(Leyenda("Bangalore", 38, "Lanzahumo"))
        leyendas.add(Leyenda("Gibraltar", 30, "Cupula de protección"))
        leyendas.add(Leyenda("Ash", 121, "Trampa arrojadiza"))
        leyendas.add(Leyenda("Horizon", 125, "Ascensor gravitacional"))
        leyendas.add(Leyenda("Wattson", 22, "Perímetro de seguridad"))
        leyendas.add(Leyenda("Mirage", 30, "Desquiciar"))




    }

    val funcionRecibeResultado =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultado ->
            val RESULT_ENVIAR = 3;
            when (resultado.resultCode) {
                RESULT_OK -> {

                }
                RESULT_CANCELED -> {
                    Toast.makeText(this, "Operacion cancelada", Toast.LENGTH_SHORT).show()
                }
                RESULT_ENVIAR -> {
                    val nombre = resultado.data?.getStringExtra("nombre")
                    val edad = resultado.data?.getStringExtra("edad")
                    val habilidad = resultado.data?.getStringExtra("habilidad")

                    leyendas.add(Leyenda(nombre!!, edad!!.toInt(), habilidad!!))
                    adapter.notifyItemInserted(leyendas.size - 1)
                }

            }
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
            R.id.aniadir->{
                val resultado = Intent(this, SecondActivity::class.java)
                funcionRecibeResultado.launch(resultado)
                true
            }
            R.id.restall -> {
                borrartodo()
                true
            }
            R.id.contacto->{
                val intent = Intent(this, ThirdActivity::class.java)
                startActivity(intent)
                true
            }
            else -> {
                false
            }
        }
    }
}



