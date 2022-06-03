package com.estech.notificationssample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.format.DateUtils
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.TaskStackBuilder
import androidx.core.graphics.drawable.IconCompat
import com.estech.notificationssample.databinding.ActivityMainBinding
import java.util.*


const val NO_BADGE_CHANNEL_ID = "myNotif2"
const val CUSTOM_CHANNEL_ID = "myNotif4"
private const val notificationId = 5

private const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()
        createNotificationChannelNoBadge()
        createCustomChannel()
        binding.notifBasic.setOnClickListener { notifBasic() }
        binding.notifPush.setOnClickListener { notifPush() }
        binding.notifCancel.setOnClickListener { cancelNotif() }
        binding.notifBigImg.setOnClickListener { notifImagenGrande() }
        binding.notifBigLittleImg.setOnClickListener { notifImagenGrandeYMiniatura() }
        binding.notifBigText.setOnClickListener { notifTextGrande() }
        binding.notifBandeja.setOnClickListener { notifBandejaEntrada() }
        binding.notifConversacion.setOnClickListener { notifConversacion() }
        binding.notifMultimedia.setOnClickListener { notifMultimedia() }
        binding.notifPendingNormal.setOnClickListener { pendingIntentNormal() }
        binding.notifPendingEspecial.setOnClickListener { pendingIntentEspecial() }
        binding.notifCustom.setOnClickListener { customNotif() }
        binding.notifSound.setOnClickListener { soundNotification() }
    }

    private fun notifBasic() {
        val builder = NotificationCompat.Builder(this, NORMAL_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_name) //icono pequeño
            .setContentTitle("Notificación básica") //titulo
            .setContentText("Esta es una notificación básica") //texto
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //prioridad para android 7.1 y anteriores
        val notificationManager =
            NotificationManagerCompat.from(this) //llamamos al gestor de notificaciones
        notificationManager.notify(
            notificationId,
            builder.build()
        ) //lanzamos con el gestor la notificación, bajo una id que la identifica
    }

    private fun notifPush() {
        //intent con la actividad a abrir
        val intent = Intent(this, SecondActivity::class.java).apply {
            flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK //flags que configuran la actividad que se inicia
        }
        //pending intent
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        //requestCode es un código que permite identificar el pendingintent que ha abierto la actividad
        //el 4º parámetro permite configurar el intent con flags
        val builder = NotificationCompat.Builder(this, NORMAL_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle("Notificación con toque")
            .setContentIntent(pendingIntent) //asigna el pending intent
            .setAutoCancel(true) //autocancela al pulsar la notificación
            .setContentText("Pulsa para abrir la actividad.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, builder.build())
    }

    private fun cancelNotif() {
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.cancel(notificationId) //elimina una notificación con dicha id de la barra de notificaciones
    }

    private fun notifImagenGrande() {
        val bm = BitmapFactory.decodeResource(
            resources,
            R.drawable.milhouse
        ) //bitmap de la imagen a mostrar
        val bigPictureStyle =
            NotificationCompat.BigPictureStyle() //se crea un objeto BigPictureStyle
        bigPictureStyle.bigPicture(bm) // se asigna una imagen
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, NORMAL_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Expandible Imagen Grande")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentText("Ésta es una notificación expandible con imagen grande")
                .setStyle(bigPictureStyle) //se indica la imagen grande a mostrar
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, builder.build())
    }

    private fun notifImagenGrandeYMiniatura() {
        val bm = BitmapFactory.decodeResource(resources, R.drawable.milhouse)
        val builder = NotificationCompat.Builder(this, NORMAL_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setContentTitle("Expandible Imagen Grande")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setLargeIcon(bm) //se pasa el bitmap para mostrar cuando la notificación esté contraíta
            .setContentText("Ésta es una notificación expandible con imagen grande")
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bm)
                    .bigLargeIcon(null)
            ) //Large icon desaparece al expandir, y vuelve a aparecer al contraer
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, builder.build())
    }

    private fun notifTextGrande() {
        val bm = BitmapFactory.decodeResource(resources, R.drawable.milhouse)
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, NORMAL_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Expandible Texto Grande")
                .setLargeIcon(bm)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentText("Notificación expandible con bloque grande de texto") // se muestra con la notificación contraida
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.texto_largo))
                ) //texto largo al expandir la notificación
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, builder.build())
    }

    private fun notifBandejaEntrada() {
        val bm = BitmapFactory.decodeResource(resources, R.drawable.milhouse)
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, NORMAL_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Bandeja de entrada")
                .setLargeIcon(bm)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentText("Tienes 2 mensajes") // se muestra con la notificación contraida
                .setStyle(
                    NotificationCompat.InboxStyle()
                        .addLine(getString(R.string.texto_largo)) //cada linea separada a mostrar en la notifiacción
                        .addLine(getString(R.string.texto_largo2))
                )
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, builder.build())
    }

    private fun notifConversacion() {
        // Persona 1
        val pBuilder = Person.Builder().setName("Milhouse")
            .setIcon(IconCompat.createWithResource(this, R.drawable.milhouse))
        val person1 = pBuilder.build()

        // Persona 2
        val pBuilder2 = Person.Builder().setName("Dr. Zoidberg")
            .setIcon(IconCompat.createWithResource(this, R.drawable.zoidberg))
        val person2 = pBuilder2.build()

        // Creamos fechas para los mensajes
        val calendar = Calendar.getInstance()
        val calendar2 = Calendar.getInstance()
        calendar2.add(Calendar.MINUTE, -3)

        // notificación 1, muestra los mensajes de la persona 1
        val notification: Notification = NotificationCompat.Builder(this, NORMAL_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setStyle(
                NotificationCompat.MessagingStyle(person1) // añade mensaje: texto, fecha en formato long y person
                    .addMessage(
                        "Todo ha salido a pedir de Milhouse",
                        calendar2.timeInMillis,
                        person1
                    )
            )
            .build()
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, notification)

        // notificación 2 para los mensajes de la persona 2
        val notification2: Notification = NotificationCompat.Builder(this, NORMAL_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setStyle(
                NotificationCompat.MessagingStyle(person2) // añade dos mensajes
                    .addMessage(
                        "¡No hay nada como la primera taza de jugo de basura en la mañana!",
                        calendar.timeInMillis,
                        person2
                    )
                    .addMessage(
                        "Por cierto, me tomé la libertad de fertilizar tu caviar.",
                        calendar2.timeInMillis,
                        person2
                    )
            )
            .build()
        notificationManager.notify(25, notification2)

        // mezcla mensajes de distintas personas en la misma notificación
        val notification3: Notification = NotificationCompat.Builder(this, NORMAL_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_name)
            .setStyle(
                NotificationCompat.MessagingStyle(person1)
                    .addMessage(
                        "¡No hay nada como la primera taza de jugo de basura en la mañana!",
                        calendar.timeInMillis,
                        person1
                    )
                    .addMessage(
                        "Por cierto, me tomé la libertad de fertilizar tu caviar.",
                        calendar2.timeInMillis,
                        person2
                    )
            )
            .build()
        notificationManager.notify(26, notification3)
    }

    private fun notifMultimedia() {
        val nirvana =
            BitmapFactory.decodeResource(resources, R.drawable.nirvana) //imagen del disco en grande

        // conjnto de pending Intent que realizan una acción determinada
        val addIntent = Intent(this, MainActivity::class.java)
        val prevPendingIntent = PendingIntent.getService(this, 0, addIntent, 0)
        val pausePendingIntent = PendingIntent.getService(this, 0, addIntent, 0)
        val nextPendingIntent = PendingIntent.getService(this, 0, addIntent, 0)
        val notification: Notification = NotificationCompat.Builder(this, NO_BADGE_CHANNEL_ID)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC) // Muestra los controles cuando la App está bloqueada
            .setSmallIcon(R.drawable.ic_stat_name) // Controles multimedia
            .addAction(R.drawable.ic_previous, "Previous", prevPendingIntent) // #id 0
            .addAction(R.drawable.ic_pause, "Pause", pausePendingIntent) // #id 1
            .addAction(R.drawable.ic_next, "Next", nextPendingIntent) // #id 2
            // Aplica el estilo multimedia
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(1)
            ) //en la vista contraída se mostrará el botón con id 1
            .setContentTitle("Wonderful music")
            .setContentText("My Awesome Band")
            .setLargeIcon(nirvana)
            .build()
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(25, notification)
    }

    private fun pendingIntentNormal() {
        val resultIntent =
            Intent(this, SecondActivity::class.java) //intent para la actividad a abrir
        val stackBuilder = TaskStackBuilder.create(this) // permite crear una pila de actividades
        stackBuilder.addNextIntentWithParentStack(resultIntent) // se incluye el intent. En Manifest ya se ha indicado cuál es la actividad padre
        val resultPending = stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT
        ) //pendingintent normal, el Flag indica que si ya existe el pendingintent, se actualice
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, NORMAL_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("PendingIntent")
                .setContentIntent(resultPending)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentText("PendingIntent abre actividad normal")
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, builder.build())
    }

    private fun pendingIntentEspecial() {
        val notifyIntent =
            Intent(this, SecondActivity::class.java) //intent para la actividad a abrir
        notifyIntent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK //con estos flags se consigue abrir una actividad nueva sin actividades padre
        // también se ha configurado la actividad en el manifest
        val notifyPending = PendingIntent.getActivity(
            this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        ) //si la actividad que quiere abrir ya está presente, actualiza los data extra si existieran
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, NORMAL_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("PendingIntent")
                .setContentIntent(notifyPending)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentText("PendingIntent abre actividad especial")
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, builder.build())
    }

    private fun customNotif() {
        // layouts para la notificación personalizada
        val collapsedView = RemoteViews(packageName, R.layout.notification_small) //carga layout
        collapsedView.setTextViewText(R.id.content_title,"Notificación personalizada") //pone texto en textview con esta id
        collapsedView.setTextViewText(R.id.content_text, "Esta es una notificación personalizada")
        //timestamp
        collapsedView.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME))
        val expandedView = RemoteViews(packageName, R.layout.notification_large)
        expandedView.setImageViewResource(R.id.big_icon, R.drawable.nirvana)
        expandedView.setTextViewText(
            R.id.timestamp,
            DateUtils.formatDateTime(this, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME)
        )
        expandedView.setTextViewText(
            R.id.notification_message,
            "Esta es una notificación personalizada"
        )
        val intent = Intent(this, SecondActivity::class.java)
        // click en botón izquierdo abre actividad
        expandedView.setOnClickPendingIntent(
            R.id.left_button,
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        )

        // Apllica los layouts a la notificación
        val customNotification = NotificationCompat.Builder(this, NORMAL_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setStyle(NotificationCompat.DecoratedCustomViewStyle()) //necesario para usar una vista personalizada
                .setCustomContentView(collapsedView) //indica la vista contraida
                .setCustomBigContentView(expandedView) //indica la vista expandida
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Expandible Imagen Grande")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentText("Ésta es una notificación expandible con imagen grande")
                .build()
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, customNotification)
    }

    private fun soundNotification() {
        val bm = BitmapFactory.decodeResource(resources, R.drawable.milhouse)

        // el canal está configurado para cambiar vibración, sonido y luces
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, CUSTOM_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Expandible Imagen Grande")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentText("Ésta es una notificación expandible con imagen grande")
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(bm)
                )
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(notificationId, builder.build())

        notificationManager.deleteNotificationChannel(CUSTOM_CHANNEL_ID)

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

    private fun createNotificationChannelNoBadge() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notificación sin insignia"
            val description = "Canal de notificaciones sin ingisnia"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NO_BADGE_CHANNEL_ID, name, importance)
            channel.description = description
            channel.setShowBadge(false) // para no mostrar el indicador de nuevas notificaciones sobre el icono
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createCustomChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "Canal personalizado"
            val description = "Canal de notificaciones personalizado"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CUSTOM_CHANNEL_ID, name, importance)
            channel.description = description
            channel.lightColor = Color.RED //establece un color al led de notificaciones
            // patrón de vibración
            channel.vibrationPattern = longArrayOf(
                100,
                30,
                100,
                30,
                100,
                200,
                200,
                30,
                200,
                30,
                200,
                200,
                100,
                30,
                100,
                30,
                100,
                100,
                30,
                100,
                30,
                100,
                200,
                200,
                30,
                200,
                30,
                200,
                200,
                100,
                30,
                100,
                30,
                100
            )
            val uri =
                Uri.parse("android.resource://" + this.packageName + "/" + R.raw.coconuts) //uri del sonido

            //constructor del sonido
            val att = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()
            channel.setSound(uri, att) //establece el sonido a la aplicación
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

}