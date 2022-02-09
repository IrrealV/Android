package com.example.ciclovida

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

            intent.putExtra("user", "sergio")
            intent.putExtra("key","asdf")

            startActivity(intent)
            finish()
        }
        val button2=findViewById<FloatingActionButton>(R.id.floatingActionButton)
        button2.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "hola")
            intent.setType("text/plain")
            startActivity(intent)
        }
    }


}