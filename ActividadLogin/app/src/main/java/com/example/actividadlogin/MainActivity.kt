package com.example.actividadlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener {
            val cambio = Intent(this, SecondActivity::class.java)

            cambio.putExtra("user", "victor.heras")
            cambio.putExtra("pass", "qasdf")

            startActivity(cambio)
            finish()
        }
    }
}