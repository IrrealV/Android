package com.example.actividadlogin

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enviar_info)

        val bundle = intent.extras

        if (bundle!!.containsKey("user") == true){
            val usuario = bundle.getString("user", "victor.heras")
            Toast.makeText(this, usuario, Toast.LENGTH_SHORT).show()
            }

        val volver = findViewById<Button>(R.id.volver)
        volver.setOnClickListener {
            val cambio = Intent(this, MainActivity::class.java)

        }
    }
}