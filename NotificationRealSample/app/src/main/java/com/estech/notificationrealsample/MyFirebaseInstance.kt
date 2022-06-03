package com.estech.notificationrealsample

import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


/**
 * Created by sergi on 02/06/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class MyFirebaseInstance : FirebaseMessagingService() {


    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("FCM", "Token: $token")
        // enviar token al servidor
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        if (message.notification != null) {
            // mensaje con datos predefinidos de android
            message.notification?.let {
                val title = it.title
                val body = it.body
            }
        }

        if (message.data != null && message.data.size > 0) {
            //mensaje con carga extra de información
        }

        if (message.data.size > 0) {
            Log.d(
                "FCM", "Message data payload: " + message.data
            )
            val title = message.data.get("title")
            val body = message.data.get("body")

            if (title != null && body != null)
                notifBasic(title, body)
        }

    }

    private fun notifBasic(title: String, body: String) {
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, NORMAL_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_name) //icono pequeño
                .setColor(ContextCompat.getColor(this, R.color.teal_200))
                .setContentTitle(title) //titulo
                .setContentText(body) //texto
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) //prioridad para android 7.1 y anteriores
        val notificationManager =
            NotificationManagerCompat.from(this) //llamamos al gestor de notificaciones
        notificationManager.notify(
            0, builder.build()
        ) //lanzamos con el gestor la notificación, bajo una id que la identifica
    }
}