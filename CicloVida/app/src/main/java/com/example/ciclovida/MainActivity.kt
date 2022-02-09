package com.example.ciclovida

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    val TAG = "CICLO_DE_VIDA"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate")

        setTitle("MyAPP")

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this,SegundaActividad::class.java)
            startActivity(intent)
        }
    }


}