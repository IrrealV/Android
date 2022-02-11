package com.example.actividadlogin

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.enviar_info)

        val bundle = intent.extras
        if (bundle!!.containsKey("user") == true){
            val usuario = bundle.getString("user")
            Toast.makeText(this, usuario, Toast.LENGTH_SHORT).show()
            }
    }
}