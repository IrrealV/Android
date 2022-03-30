package com.estech.examen2trimestre

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.estech.examen2trimestre.databinding.ActivityAcercaDeBinding
import java.util.*
import java.util.concurrent.TimeUnit

class AcercaDe : AppCompatActivity() {
    private lateinit var binding: ActivityAcercaDeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcercaDeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar2)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Glide
            .with(this)
            .load(R.drawable.gato_moviendo_cabeza)
            .into(binding.ivGif)

        hacerCuentaAtras()
    }

    /**
     * función para crear la cuenta atrás
     */
    private fun hacerCuentaAtras() {

        // obtenemos fechas
        val fechaActual = Calendar.getInstance()
        val fechaFinal = Calendar.getInstance()
        // configuramos fecha final
        fechaFinal.set(Calendar.DAY_OF_MONTH, 30)
        fechaFinal.set(Calendar.HOUR_OF_DAY, 10)
        fechaFinal.set(Calendar.MINUTE, 40)

        // con esta operación se obtienen los milisegundos restantes hasta la fecha tope
        // TODO valor diferencia = -3479999
        val diferencia = fechaFinal.timeInMillis - fechaActual.timeInMillis

        // función para hacer una cuenta atrás, se activa cada segundo, dura el valor de diferencia
        val counter = object : CountDownTimer(diferencia, 1000) {
            override fun onTick(p0: Long) {
                // milisegundos hasta la fecha tope
                var millisHastaTerminar = p0

                //se van obteniendo días, horas, minutos y segundos
                val days: Long = TimeUnit.MILLISECONDS.toDays(millisHastaTerminar)
                millisHastaTerminar -= TimeUnit.DAYS.toMillis(days)

                val hours: Long = TimeUnit.MILLISECONDS.toHours(millisHastaTerminar)
                millisHastaTerminar -= TimeUnit.HOURS.toMillis(hours)

                val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(millisHastaTerminar)
                millisHastaTerminar -= TimeUnit.MINUTES.toMillis(minutes)

                val seconds: Long = TimeUnit.MILLISECONDS.toSeconds(millisHastaTerminar)

                binding.tvCuentaAtras.text =
                    "$days día $hours horas $minutes minutos $seconds segundos"
            }

            override fun onFinish() {
                binding.tvCuentaAtras.text = "Tiempo terminado"
            }
        }
        counter.start()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}