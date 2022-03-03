package com.example.appconversor

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.appconversor.databinding.ActivityMainBinding


class MainActivity() : AppCompatActivity(){
    var minutosTotales = 0
    var min = 0
    var horas = 0
    var secspin = String()
    var duracion = 0
    var resultado = 0
    var quecrack = String()
    var checker = false

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this,"Porfavor Introduzca su tiempo libre", Toast.LENGTH_SHORT).show()

//Ocultamos todos los valores que no queremos que aparezcan por ahora
        desaparece()
//Ocultamos todos los valores que no queremos que aparezcan por ahora


 //Esto hace que el EditTexView y el TextView cambien a la vez
        binding.horas.addTextChangedListener(){
            @Override
            binding.totalhoras.text = binding.horas.text
            if(binding.horas.text.toString() == ""){
                binding.totalhoras.text = "00"
                Toast.makeText(this,"Porfavor Introduzca su tiempo libre", Toast.LENGTH_SHORT).show()
            }
            else if(binding.horas.text.toString().toInt() > 12){
                 binding.totalhoras.text = "11"
            }

        }
        binding.minutos.addTextChangedListener(){
            @Override
            binding.totalmin.text = binding.minutos.text
            if(binding.minutos.text.toString() == ""){
                binding.totalmin.text = "00"
            }
            else if(binding.minutos.text.toString().toInt() > 60){
                 binding.totalmin.text = "59"
            }

        }
//Esto hace que el EditTexView y el TextView cambien a la vez



//Esto hace que el spinner se vuelva visible dependiendo de la cantidad de horas que hayamos metido
        binding.totalmin.addTextChangedListener() {
            // Si los if no están especificados la app crahsea en el momento de borrar los números
            if((binding.totalmin.text.toString() == "")  || (binding.totalmin.text.toString() == "00")){
                desaparece()
            }
            else{checkMinutos()
            }
        }
        binding.totalhoras.addTextChangedListener() {
            // Si los if no están especificados la app crahsea en el momento de borrar los números
            if((binding.totalhoras.text.toString()== "00")||(binding.totalhoras.text.toString()== "")){desaparece()}
            else{checkMinutos()}
        }
//Esto hace que el spinner se vuelva visible dependiendo de la cantidad de horas que hayamos metido



//Esto hace que los spinners y las imagenes cambien dependiendo de la elección
        binding.accion.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(binding.accion.selectedItem.toString()){
                    "Ducha"->{
                        binding.trabajar.stopPlayback()
                        binding.trabajar.visibility = View.GONE
                        binding.accionfoto.setBackgroundResource(R.drawable.ducha)
                        binding.accionfoto.visibility = View.VISIBLE

                    //Declarando las variables para el segundo spinner
                        val Ducha = ArrayAdapter.createFromResource(this@MainActivity, R.array.Ducha,R.layout.spinner_color)
                        binding.medicion.adapter = Ducha
                        Ducha.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

                        binding.medicion.visibility = View.VISIBLE
                    //Declarando las variables para el segundo spinner
                    }

                    "Youtube" ->{
                        binding.trabajar.stopPlayback()
                        binding.trabajar.visibility = View.GONE
                        binding.accionfoto.setBackgroundResource(R.drawable.youtub)
                        binding.accionfoto.visibility = View.VISIBLE

                        //Declarando las variables para el segundo spinner

                        val Youtube = ArrayAdapter.createFromResource(this@MainActivity, R.array.Youtube,R.layout.spinner_color)
                        binding.medicion.adapter = Youtube
                        Youtube.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

                        binding.medicion.visibility = View.VISIBLE
                        //Declarando las variables para el segundo spinner
                    }

                    "TikTok" ->{
                        binding.trabajar.stopPlayback()
                        binding.trabajar.visibility = View.GONE
                        binding.accionfoto.setBackgroundResource(R.drawable.tiktok)
                        binding.accionfoto.visibility = View.VISIBLE

                        //Declarando las variables para el segundo spinner

                        val TikTok = ArrayAdapter.createFromResource(this@MainActivity, R.array.TikTok,R.layout.spinner_color)
                        binding.medicion.adapter = TikTok
                        TikTok.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

                        binding.medicion.visibility = View.VISIBLE
                        //Declarando las variables para el segundo spinner

                    }

                    "Partida" ->{
                        binding.trabajar.stopPlayback()
                        binding.trabajar.visibility = View.GONE
                        binding.accionfoto.setBackgroundResource(R.drawable.partida)
                        binding.accionfoto.visibility = View.VISIBLE

                        //Declarando las variables para el segundo spinner

                        val Partida = ArrayAdapter.createFromResource(this@MainActivity, R.array.Partida,R.layout.spinner_color)
                        binding.medicion.adapter = Partida
                        Partida.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

                        binding.medicion.visibility = View.VISIBLE
                        //Declarando las variables para el segundo spinner

                    }


                    "Serie" ->{
                        binding.trabajar.stopPlayback()
                        binding.trabajar.visibility = View.GONE
                        binding.accionfoto.setBackgroundResource(R.drawable.netflix)
                        binding.accionfoto.visibility = View.VISIBLE

                        //Declarando las variables para el segundo spinner

                        val Serie = ArrayAdapter.createFromResource(this@MainActivity, R.array.Serie,R.layout.spinner_color)
                        binding.medicion.adapter = Serie
                        Serie.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

                        binding.medicion.visibility = View.VISIBLE
                        //Declarando las variables para el segundo spinner

                    }

                    "Tarea" ->{
                        binding.trabajar.stopPlayback()
                        binding.trabajar.visibility = View.GONE
                        binding.accionfoto.setBackgroundResource(R.drawable.tarea)
                        binding.accionfoto.visibility = View.VISIBLE

                        //Declarando las variables para el segundo spinner

                        val Tarea = ArrayAdapter.createFromResource(this@MainActivity, R.array.Tarea,R.layout.spinner_color)
                        binding.medicion.adapter = Tarea
                        Tarea.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

                        binding.medicion.visibility = View.VISIBLE
                        //Declarando las variables para el segundo spinner

                    }

                    "Cocinar" ->{
                        binding.trabajar.stopPlayback()
                        binding.trabajar.visibility = View.GONE
                        binding.accionfoto.setBackgroundResource(R.drawable.cocinar)
                        binding.accionfoto.visibility = View.VISIBLE

                        //Declarando las variables para el segundo spinner

                        val Cocinar = ArrayAdapter.createFromResource(this@MainActivity, R.array.Cocinar,R.layout.spinner_color)
                        binding.medicion.adapter = Cocinar
                        Cocinar.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

                        binding.medicion.visibility = View.VISIBLE
                        //Declarando las variables para el segundo spinner

                    }

                    "Programar" ->{
                        binding.trabajar.stopPlayback()
                        binding.trabajar.visibility = View.GONE
                        binding.accionfoto.setBackgroundResource(R.drawable.programador)
                        binding.accionfoto.visibility = View.VISIBLE

                        //Declarando las variables para el segundo spinner

                        val Programar = ArrayAdapter.createFromResource(this@MainActivity, R.array.Programar,R.layout.spinner_color)
                        binding.medicion.adapter = Programar
                        Programar.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

                        binding.medicion.visibility = View.VISIBLE
                        //Declarando las variables para el segundo spinner

                    }

                    "Siesta" ->{
                        binding.accionfoto.visibility = View.GONE
                        binding.trabajar.setVideoPath("android.resource://" + packageName + "/" + R.raw.trabajar)
                        binding.trabajar.start()

                        binding.trabajar.visibility = View.VISIBLE

                        //Declarando las variables para el segundo spinner

                        val Siesta = ArrayAdapter.createFromResource(this@MainActivity, R.array.Siesta,R.layout.spinner_color)
                        binding.medicion.adapter = Siesta
                        Siesta.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

                        binding.medicion.visibility = View.VISIBLE
                        //Declarando las variables para el segundo spinner

                    }

                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                desaparece()
            }
        }
        binding.medicion.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(binding.medicion.selectedItem.toString()){
                    "AudioLibro"->{
                        duracion = 120
                        binding.medicionfoto.setBackgroundResource(R.drawable.audiolibro)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "Podcast"->{
                        duracion = 60
                        binding.medicionfoto.setBackgroundResource(R.drawable.podcast)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "Cancion"->{
                        duracion = 3
                        binding.medicionfoto.setBackgroundResource(R.drawable.cancion)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "Midudev"->{
                        duracion = 30
                        binding.medicionfoto.setBackgroundResource(R.drawable.midudev)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "Veggetta777"->{
                        duracion = 15
                        binding.medicionfoto.setBackgroundResource(R.drawable.veggetta777)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "ZellenDust"->{
                        duracion = 10
                        binding.medicionfoto.setBackgroundResource(R.drawable.zellen)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "pongamoslo a prueba"->{
                        duracion = 1
                        binding.medicionfoto.setBackgroundResource(R.drawable.pongamosloaprueba)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "kfc_es"->{
                        minutosTotales *= 60
                        duracion = 10
                        binding.medicionfoto.setBackgroundResource(R.drawable.kfces)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "InformativoAngelMartin"->{
                        duracion = 1
                        binding.medicionfoto.setBackgroundResource(R.drawable.angelmartin)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "LoL"->{
                        duracion = 40
                        binding.medicionfoto.setBackgroundResource(R.drawable.lol)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "CS:GO"->{
                        duracion = 30
                        binding.medicionfoto.setBackgroundResource(R.drawable.csgo)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "Apex"->{
                        duracion = 20
                        binding.medicionfoto.setBackgroundResource(R.drawable.apex)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "Peaky Blinders"->{
                        duracion = 60
                        binding.medicionfoto.setBackgroundResource(R.drawable.peakyblinder)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "Jojos"->{
                        duracion = 18
                        binding.medicionfoto.setBackgroundResource(R.drawable.jojos)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "Hora de Aventuras"->{
                        duracion = 11
                        binding.medicionfoto.setBackgroundResource(R.drawable.horadeaventura)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "Programacion"->{
                        duracion = 190
                        binding.medicionfoto.setBackgroundResource(R.drawable.programacion)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "Android"->{
                        duracion = 300
                        binding.medicionfoto.setBackgroundResource(R.drawable.android)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "BBDD"->{
                        duracion = 120
                        binding.medicionfoto.setBackgroundResource(R.drawable.bbdd)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "Macarrones"->{
                        duracion = 30
                        binding.medicionfoto.setBackgroundResource(R.drawable.macarrones)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "Pizza"->{
                        duracion = 120
                        binding.medicionfoto.setBackgroundResource(R.drawable.pizza)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "Carne con Tomate"->{
                        duracion = 50
                        binding.medicionfoto.setBackgroundResource(R.drawable.carne_con_tomate)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "Kotlin"->{
                        duracion = 420
                        binding.medicionfoto.setBackgroundResource(R.drawable.kotlin)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "JavaScript"->{
                        duracion = 225
                        binding.medicionfoto.setBackgroundResource(R.drawable.js)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "C#"->{
                        duracion = 120
                        binding.medicionfoto.setBackgroundResource(R.drawable.csharp)
                        binding.medicionfoto.visibility = View.VISIBLE
                        total()
                    }
                    "suerte"->{
                        quecrack = "Epico, vas a dormir"
                        binding.medicionfoto.setBackgroundResource(R.drawable.epico)
                        binding.medicionfoto.visibility = View.VISIBLE
                    }

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                binding.medicion.visibility = View.GONE
                binding.medicionfoto.visibility = View.GONE
            }

        }
//Esto hace que el segundo spinner cambie de valor dependiendo de la elección


//Esto hace que el boton envie los datos a la segunda pantalla
        binding.reload.setOnClickListener{
            if(checker){
                val cambio = Intent(this, SecondActivity::class.java)
                val horas = binding.totalhoras.text.toString()
                val minutos = binding.totalmin.text.toString()
                cambio.putExtra("resultado", resultado.toString())
                cambio.putExtra("accion", binding.accion.selectedItem.toString())
                cambio.putExtra("medicion", binding.medicion.selectedItem.toString())
                cambio.putExtra("minutos", minutos)
                cambio.putExtra("horas",horas)

                startActivity(cambio)
                finish()
            }
            else{
                Toast.makeText(this, "No ha introducido suficientes datos" , Toast.LENGTH_SHORT).show()
            }
        }
//Esto hace que el boton envie los datos a la segunda pantalla
    }
    fun checkMinutos () {
        horas =  binding.totalhoras.text.toString().toInt()*60
        min = binding.totalmin.text.toString().toInt()
        if (((min.toString() != "") && (horas.toString() != "")) || ((horas.toString() != "00") && min.toString() != "00")){
            minutosTotales = horas + min
        }
            when(minutosTotales){
                0->{
                    desaparece()
                }
                in 1..240 ->{
                    binding.accion.visibility = View.VISIBLE
                    val tiempocorto = ArrayAdapter.createFromResource(
                        this,
                        R.array.tiempocorto,
                        R.layout.spinner_color
                    )
                    binding.accion.adapter = tiempocorto
                    tiempocorto.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

                }
                in 241..480 ->{
                    binding.accion.visibility = View.VISIBLE
                    val tiempomedio = ArrayAdapter.createFromResource(
                        this,
                        R.array.tiempomedio,
                        R.layout.spinner_color
                    )
                    binding.accion.adapter = tiempomedio
                    tiempomedio.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

                }
                in 481..720 ->{
                    binding.accion.visibility = View.VISIBLE
                    val tiempolargo = ArrayAdapter.createFromResource(
                        this,
                        R.array.tiempolargo,
                        R.layout.spinner_color
                    )
                    binding.accion.adapter = tiempolargo
                    tiempolargo.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
                }
                else ->{
                    desaparece()
                }

            }



        }

    fun desaparece (){
        binding.accion.visibility = View.INVISIBLE
        binding.accionfoto.visibility = View.GONE
        binding.medicion.visibility = View.GONE
        binding.medicionfoto.visibility = View.GONE
        binding.trabajar.visibility = View.GONE
    }

    fun total(){
        if(binding.medicion.selectedItem.toString() == "kfc_es"){
            resultado = (minutosTotales*60) / duracion
        }
        else{
            resultado = minutosTotales / duracion
        }
        checker = true
    }

}

