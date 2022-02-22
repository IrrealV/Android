package com.example.appconversor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.example.appconversor.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
    class MainActivity : AppCompatActivity(), TextWatcher {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    //Esto hace que el EditTexView y el TextView cambien a la vez
        binding.horas.addTextChangedListener(){
            @Override
            binding.totalhoras.text = binding.horas.text
            if(binding.horas.text == null || binding.horas.text.toString() == ""){
                binding.totalhoras.text = "00"
            }
            else if(binding.horas.text.toString().toInt() >= 24){
                binding.totalhoras.text = "23"
            }
        }

        binding.minutos.addTextChangedListener(){
            @Override
            binding.totalmin.text = binding.minutos.text
            if(binding.minutos.text == null || binding.minutos.text.toString() == ""){
                binding.totalmin.text = "00"
            }
            else if(binding.minutos.text.toString().toInt() >= 60){
                binding.totalmin.text = "59"
            }
        }
    //Esto hace que el EditTexView y el TextView cambien a la vez











    //Actividades que duran poco tiempo-------------------------------------------------------------
        val tiempocorto = ArrayAdapter.createFromResource(
            this,
            R.array.tiempocorto,
            R.layout.spinner_color
        )
        binding.accion.adapter = tiempocorto
        tiempocorto.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
    //Actividades que duran poco tiempo-------------------------------------------------------------

    //Actividades que duran tiempo medio------------------------------------------------------------
        val tiempomedio = ArrayAdapter.createFromResource(
            this,
            R.array.tiempomedio,
            R.layout.spinner_color
        )
        binding.accion.adapter = tiempomedio
        tiempomedio.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
    //Actividades que duran tiempo medio------------------------------------------------------------

    //Actividades que duran mucho tiempo------------------------------------------------------------
        val tiempolargo = ArrayAdapter.createFromResource(
            this,
            R.array.tiempolargo,
            R.layout.spinner_color
        )
        binding.accion.adapter = tiempolargo
        tiempolargo.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
    //Actividades que duran mucho tiempo------------------------------------------------------------


        }
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){}
    override fun afterTextChanged(p0: Editable?) {}
    }

