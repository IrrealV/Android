package com.estech.retrofitsample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            miFuncionQueBloquea()
        }
    }

    suspend fun miFuncionQueBloquea() {
        log("Se inicia mi función")
        delay(3000) //Simula una tarea pesada
        log("Se acaba mi función")
    }

    fun log(message: String) {
        Log.d("Corrutinas", message)
    }


    suspend fun getInfo() {                      // Dispatchers.Main
        val result = get("developer.android.com")// Dispatchers.Main
        show(result)                             // Dispatchers.Main
    }

    suspend fun get(url: String) =               // Dispatchers.Main
        withContext(Dispatchers.IO) {            // Dispatchers.IO (operaciones red o E/S)
            /* descarga datos de internet */     // Dispatchers.IO (fuera hilo principal)
        }                                        // Dispatchers.Main


    fun show(result: Any) {

    }
}