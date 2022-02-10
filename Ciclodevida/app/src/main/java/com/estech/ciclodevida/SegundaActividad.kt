package com.estech.ciclodevida

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SegundaActividad: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dos)

        val bundle = intent.extras

        if (bundle!!.containsKey("user") == true) {
            val username = bundle.getString("user")
            Toast.makeText(this, username, Toast.LENGTH_SHORT).show()
        }

    }
}