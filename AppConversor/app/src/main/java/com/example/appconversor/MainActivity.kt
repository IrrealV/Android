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
import kotlin.properties.Delegates

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
            if(binding.horas.text.toString() == ""){
                binding.totalhoras.text = "0"
            }
            else if(binding.horas.text.toString().toInt() > 12){
                "11".also { binding.totalhoras.text = it }
            }

        }

        binding.minutos.addTextChangedListener(){
            @Override

            binding.totalmin.text = binding.minutos.text
            if(binding.minutos.text.toString() == ""){
                binding.totalmin.text = "0"
            }

            else if(binding.minutos.text.toString().toInt() > 60){
                "59".also { binding.totalmin.text = it }
            }


        }
    //Esto hace que el EditTexView y el TextView cambien a la vez


    //Esto hace que el spinner se vuelva visible dependiendo de la cantidad de horas que hayamos metido

        binding.totalhoras.addTextChangedListener(){
            @Override
            //Condiciones para el array principal cambiante

                if(binding.totalhoras.text.toString()== "0" && binding.totalmin.text.toString()== "0"||binding.totalhoras.text.toString()== "" && binding.totalmin.text.toString()== ""){
                    binding.accion.visibility = View.INVISIBLE
                    Toast.makeText(this,binding.totalhoras.toString().toInt(), Toast.LENGTH_SHORT).show()


                }
                //Condiciones para el array principal cambiante
                //Actividades que duran poco tiempo---------------------------------------------------------
                else{
                    var total = binding.totalhoras.text.toString().toInt()/60 + binding.totalhoras.text.toString().toInt()
                    if(total <= 180){
                        binding.accion.visibility = View.VISIBLE
                        val tiempocorto = ArrayAdapter.createFromResource(
                            this,
                            R.array.tiempocorto,
                            R.layout.spinner_color
                        )
                        binding.accion.adapter = tiempocorto
                        tiempocorto.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

                    }
                    //Actividades que duran poco tiempo-------------------------------------------------------------
                    //Actividades que duran tiempo medio------------------------------------------------------------
                    else if(total <= 420){
                        binding.accion.visibility = View.VISIBLE
                        val tiempomedio = ArrayAdapter.createFromResource(
                            this,
                            R.array.tiempomedio,
                            R.layout.spinner_color
                        )
                        binding.accion.adapter = tiempomedio
                        tiempomedio.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

                    }
                    //Actividades que duran tiempo medio------------------------------------------------------------
                    //Actividades que duran mucho tiempo------------------------------------------------------------
                    else if(total<=720){
                        binding.accion.visibility = View.VISIBLE
                        val tiempolargo = ArrayAdapter.createFromResource(
                            this,
                            R.array.tiempolargo,
                            R.layout.spinner_color
                        )
                        binding.accion.adapter = tiempolargo
                        tiempolargo.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)

                    }

                }


            //Actividades que duran mucho tiempo------------------------------------------------------------
            //Condiciones para el array principal cambiante

        }


    //Esto hace que el spinner se vuelva visible dependiendo de la cantidad de horas que hayamos metido











        }
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){}
    override fun afterTextChanged(p0: Editable?) {}
    }

