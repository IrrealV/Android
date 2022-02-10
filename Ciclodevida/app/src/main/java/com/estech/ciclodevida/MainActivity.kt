package com.estech.ciclodevida

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    val TAG = "CICLO_DE_VIDA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate")

        setTitle("MyApp")

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, SegundaActividad::class.java)

            intent.putExtra("user", )
            intent.putExtra("pass", "qwerasdf")

            startActivity(intent)
            finish()
        }

        val button2 = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        button2.setOnClickListener {
//            val intent = Intent()
//            intent.action = Intent.ACTION_SEND
//            intent.putExtra(Intent.EXTRA_TEXT, "hola")
//            intent.setType("text/plain")
//            startActivity(intent)

            val uri = Uri.parse("https://www.escuelaestech.es/")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            startActivity(intent)
        }


    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }
}