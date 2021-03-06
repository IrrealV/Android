package com.example.appgatos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.appgatos.adapter.GatoAdapter
import com.example.appgatos.databinding.ActivityMainBinding
import com.example.appgatos.dataclass.Gato

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Quieres salir de la aplicación?")
        builder.setPositiveButton("Si") { dialog, which ->
            finish()
        }
        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(this, "¡Gracias por quedarte!", Toast.LENGTH_SHORT).show()
        }
        val dialog = builder.create()
        dialog.show()
        return true
    }




}