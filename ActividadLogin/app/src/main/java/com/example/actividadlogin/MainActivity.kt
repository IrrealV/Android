package com.example.actividadlogin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    val TAG = "CICLO_DE_VIDA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login = findViewById<Button>(R.id.login)
        val user = findViewById<TextView>(R.id.user)
        val passwd = findViewById<TextView>(R.id.passwd)
        val call = findViewById<FloatingActionButton>(R.id.llamada)
        val slider = findViewById<SeekBar>(R.id.cantIngres)
        val inver = findViewById<TextView>(R.id.inversion)
        val restart = findViewById<Button>(R.id.reiniciar)
        val min = findViewById<TextView>(R.id.precmin)
        val max = findViewById<TextView>(R.id.precmax)
        val spin = findViewById<Spinner>(R.id.spinner)
        val proguess = 100

        val dineros = arrayOf("Euro", "Dolar", "Libra", "Rupia")
        val adapterDineros = ArrayAdapter(this, android.R.layout.simple_spinner_item,dineros)
        spin.adapter = adapterDineros


        call.setOnClickListener {
            val marca = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "658204691"))
            startActivity(marca)
        }

        slider.setOnSeekBarChangeListener( object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(SeekBar: SeekBar, proguess: Int, fromUser: Boolean) {

                if(proguess < 5000 && proguess > 100){
                    var currentprogress = proguess + 1
                    inver.text = "$currentprogress"+""
                }
                else if(proguess >= 5000){
                    inver.text = "5000"+""
                }
                else if(proguess <= 100){
                    inver.text = "100"+""
                }

            }
            override fun onStartTrackingTouch(SeekBar: SeekBar?) {}
            override fun onStopTrackingTouch(SeekBar: SeekBar?) {}
        })

        login.setOnClickListener {
            if (passwd.text.toString() == "qwerasdf") {
                val cambio = Intent(this, SecondActivity::class.java)
                val value = user.text.toString()
                val inversion = inver.text.toString()
                cambio.putExtra("user", value)
                cambio.putExtra("inver",inversion)



                startActivity(cambio)
                finish()
            }
            else {
                Toast.makeText(this, "La contraseña es incorrecta" , Toast.LENGTH_SHORT).show()
            }
        }

        restart.setOnClickListener {
            finish()
            startActivity(getIntent())
        }





    }
}