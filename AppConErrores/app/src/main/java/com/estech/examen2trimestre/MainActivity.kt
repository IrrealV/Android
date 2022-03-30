package com.estech.examen2trimestre

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.estech.examen2trimestre.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MyAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // crear lista de mensajes fijos
        val mensaje1 = Mensaje("Entregar tarea Android", "2022/03/22 23:59:33")
        val mensaje2 = Mensaje("Estudiar para el examen", "2022/03/29 16:00:22")
        val mensaje3 = Mensaje("Entregar proyecto", "2022/04/01 10:00:!1")
        val listado = mutableListOf(mensaje1, mensaje2, mensaje3)

        // configuración del adapter
        val layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(listado)
        binding.recyclerview.layoutManager = layoutManager
        binding.recyclerview.adapter = adapter

        // listener el botón enviar
        binding.botonEnviar.setOnClickListener { enviarMensaje() }

        // call back para el listener de arrastrar celda
        val arrastraParaEliminar = ItemTouchHelper(simpleCallback)
        arrastraParaEliminar.attachToRecyclerView(binding.recyclerview)
    }

    /**
     * función para crear un añadir un mensaje
     * si el campo no está vacío, obtiene la fecha actual, crea un objeto mensaje y llama a la
     * función aniadirTarea del adaptador
     */
    private fun enviarMensaje() {
        val tarea = binding.campotexto.text.toString()

        if (tarea.isNotEmpty()) {
            val fechaHoy = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyy/MM/dd HH-mm:ss", Locale.getDefault())
            val textoFecha = sdf.format(fechaHoy.time)

            val mensaje = Mensaje(tarea, textoFecha)
            adapter.aniadirTarea(mensaje)

            binding.campotexto.setText("")
        }
    }

    // Callback que detecta cuando se arrastra una celda a izquierda o derecha.
    // La función onSwiped llama a la función eliminarTarea del adapter
    private val simpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.START or ItemTouchHelper.END
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            adapter.eliminarTarea(viewHolder.adapterPosition)
        }
    }
}