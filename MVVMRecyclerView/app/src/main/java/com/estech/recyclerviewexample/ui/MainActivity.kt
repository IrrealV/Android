package com.estech.recyclerviewexample.ui

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.estech.recyclerviewexample.databinding.ActivityMainBinding
import com.estech.recyclerviewexample.viewmodel.JugadoresViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter
    private lateinit var jugadorViewModel: JugadoresViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configRecycler()
        jugadorViewModel = ViewModelProvider(this).get(JugadoresViewModel::class.java)

        jugadorViewModel.listaJugadores.observe(this) {
            adapter.update(it)
            binding.editTextName.setText("")
            binding.editTextTextPosicion.setText("")
            binding.editTextDorsal.setText("")
            mostrarTeclado(false)

        }

        binding.buttonAdd.setOnClickListener { addPlayer() }

        jugadorViewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun configRecycler() {
        val layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter()
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = adapter
    }

    private fun addPlayer() {
        val name = binding.editTextName.text.toString()
        val position = binding.editTextTextPosicion.text.toString()
        val number = binding.editTextDorsal.text.toString()

        jugadorViewModel.addJugador(name, position, number)
        binding.recycler.scrollToPosition(adapter.itemCount - 1)
    }

    private fun mostrarTeclado(showKeyboard: Boolean) {
        val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (currentFocus != null) {
            inputManager.hideSoftInputFromWindow(
                currentFocus!!.windowToken,
                if (showKeyboard) InputMethodManager.SHOW_FORCED else InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}