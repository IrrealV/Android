package com.example.appconversor

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.appconversor.databinding.ActivityMainBinding
import com.example.appconversor.databinding.ActivitySecondBinding

class SecondActivity() : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var bundle = intent.extras

        when(bundle!!.getString("medicion")){
            "AudioLibro"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.audiolibro)

            }
            "Podcast"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.podcast)

            }
            "Cancion"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.cancion)

            }
            "Midudev"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.midudev)

            }
            "Veggetta777"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.veggetta777)

            }
            "ZellenDust"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.zellen)

            }
            "pongamoslo a prueba"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.pongamosloaprueba)

            }
            "kfc_es"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.kfces)

            }
            "InformativoAngelMartin"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.angelmartin)

            }
            "LoL"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.LoL)

            }
            "CS:GO"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.CSGO)

            }
            "Apex"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.apex)

            }
            "Peaky Blinders"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.peakyblinder)

            }
            "Jojos"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.jojos)

            }
            "Hora de Aventuras"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.horadeaventura)

            }
            "Programacion"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.programacion)

            }
            "Android"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.android)

            }
            "BBDD"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.bbdd)

            }
            "Macarrones"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.macarrones)

            }
            "Pizza"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.pizza)

            }
            "Carne con Tomate"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.carne_con_tomate)

            }
            "Kotlin"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.kotlin)

            }
            "JavaScript"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.js)

            }
            "C#"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.Csharp)

            }
            "suerte"->{

                binding.imagenresultado.setBackgroundResource(R.drawable.epico)

            }

        }

        val horas = bundle.getString("horas")
        binding.totalhoras2.text = horas

        val minutos = bundle.getString("minutos")
        binding.totalmin2.text = minutos

        val total = bundle.getString("resultado")
        binding.totalmins.text = total

        val medicion = bundle.getString("medicion")
        binding.medtotal.text = medicion

        val accion = bundle.getString("accion")
        binding.solucion.text = accion

        binding.volver.setOnClickListener {
            val cambio = Intent(this, MainActivity::class.java)
            startActivity(cambio)
            finish()
        }

        binding.compartir.setOnClickListener {
            val correo = Intent(Intent.ACTION_SENDTO)
            correo.data = Uri.parse("mailto:") //solo apps de email lo abrirán
            correo.putExtra(Intent.EXTRA_EMAIL, arrayOf("vmherasdurillo@hotmail.com"))
            correo.putExtra(Intent.EXTRA_SUBJECT, "Transformacion Tiempo")
            correo.putExtra(Intent.EXTRA_TEXT, "$horas:$minutos son $total de $medicion ")
            startActivity(correo)
        }


    }
}