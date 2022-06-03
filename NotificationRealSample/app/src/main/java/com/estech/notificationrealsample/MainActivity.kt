package com.estech.notificationrealsample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.ktx.messaging


const val NORMAL_CHANNEL_ID = "myNotif"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()
        getToken()
        suscribirTema()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel_name = "Notificaciones de prueba" //nombre canal
            val channel_description = "Probando notificaciones en una App de ejemplo" //descripción
            val importance =
                NotificationManager.IMPORTANCE_DEFAULT //Prioridad o importancia de la notificación
            val channel =
                NotificationChannel(
                    NORMAL_CHANNEL_ID,
                    channel_name,
                    importance
                ) //construye el canal
            channel.description = channel_description // añade una descripción
            val notificationManager = getSystemService(
                NotificationManager::class.java
            ) //Se obtiene el gestor de notificaciones
            notificationManager.createNotificationChannel(channel) //se crea el canal de notificaciones
        }
    }

    private fun getToken() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener { token ->
            // Token de Registro FCM

            val msg = "Token: $token"
            Log.d("FCM", msg)
        }
    }

    private fun suscribirTema() {
        Firebase.messaging.subscribeToTopic("tiempo")
            .addOnCompleteListener { task ->
                val msg = if (!task.isSuccessful) "Suscrito con éxito"
                else "Error al suscribirse"
                Log.d("FCM", msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
    }

    private fun desuscribir() {
        Firebase.messaging.unsubscribeFromTopic("tiempo")
            .addOnCompleteListener {task ->
                val msg = if (!task.isSuccessful) "Eliminado con éxito"
                else "Error al eliminar la suscripción"
                Log.d("FCM", msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
    }
}