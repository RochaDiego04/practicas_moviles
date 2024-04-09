package com.example.notifications

import com.example.notifications.databinding.ActivityMainBinding
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val canalNombre = "Diego Rocha Escamilla"
    private val canalId = "canalId"
    private val notificacionId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotificacion.setOnClickListener {
            crearCanalNotificacion()
            crearNotificacion()
        }
    }

    private fun crearCanalNotificacion(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canalImportancia = NotificationManager.IMPORTANCE_HIGH
            val canal = NotificationChannel(canalId,canalNombre,canalImportancia)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(canal)
        }
    }

    private fun crearNotificacion (){
        var notificacion = NotificationCompat.Builder (this, canalId).also {
            it.setContentTitle("Notificacion")
            it.setContentText("Cuerpo de la notificación")
            it.setSmallIcon(R.drawable.icon_mensaje)
            it.priority = NotificationCompat.PRIORITY_HIGH
        }.build()
        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS

            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(notificacionId,notificacion)
    }

}