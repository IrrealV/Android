package com.example.actividadlogin

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enviar_info)
        val user = findViewById<TextView>(R.id.usuario)
        val cash = findViewById<TextView>(R.id.cash)
        val bundle = intent.extras

        //Por algún motivo que no entiendo los Toast hacen que la app crashee al ser usados
        //junto con los .text, igualmente los he dejado por si son necesarios


        if (bundle!!.containsKey("user")){
            val popup = bundle.getString("user")
            //Toast.makeText(this, popup, Toast.LENGTH_SHORT).show()
            user.text = popup
        }

        if(bundle!!.containsKey("inver")){
            val result = bundle.getString("inver")
            //Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            cash.text = result
        }




        val volver = findViewById<Button>(R.id.volver)
        volver.setOnClickListener {
            val cambio = Intent(this, MainActivity::class.java)
            startActivity(cambio)
            finish()
        }
    }
}