package com.example.kcount

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.kcount.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val preferencias = getSharedPreferences("pref", MODE_PRIVATE) //acabo de aprender a usar las preferences a 30/30/2022 a las 21:22
        val usuario = preferencias.getString("user","")
        binding.user.setText(usuario)


        //Click cara slayer
        binding.caraslayer.setOnClickListener {
            val user = binding.user.text.toString() //Los usuarios disponibles son: Humano, Slayer y Demonio
            val password = binding.passwd.text.toString()//Las contraseñas son las mismas que en documento: 123456, qwerasdf y asdfasdf
            compruebaLogin(user, password)  //Si, lo he cogido del examen, solo que en vez de if he usado un when ajajajaja
        }




    }

    private  fun compruebaLogin(user: String, pass: String) {

        val builder = AlertDialog.Builder(this)
        when {
            pass.isEmpty() && user.isEmpty() -> {

                builder.setTitle("Vas a continuar como invitado ¿Estas de acuerdo?")
                builder.setPositiveButton("Si") { dialog, which ->
                    Toast.makeText(this, "Se abre el Main Activity", Toast.LENGTH_SHORT).show()
                    binding.caraslayer.setBackgroundResource(R.drawable.facedead)
                    abrirMain(user)
                }
                builder.setNegativeButton("No") { dialog, which ->
                    Toast.makeText(
                        this,
                        "Por favor introduzca su usuario y contraseña",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                val dialog = builder.create()
                dialog.show()
            }

            user.isEmpty() -> {
                binding.user.error = "El campo está vacío"
            }

            pass.isEmpty() -> {
                binding.passwd.error = "El campo está vacío"
            }

            user == "Humano" && pass == "123456" -> {
                Toast.makeText(this, "Ha iniciado sesion como $user", Toast.LENGTH_SHORT).show()
                binding.caraslayer.setBackgroundResource(R.drawable.doomguyface)
                abrirMain(user)
            }

            user == "Slayer" && pass == "qwerasdf" -> {
                Toast.makeText(this, "Ha iniciado sesion como $user", Toast.LENGTH_SHORT).show()
                binding.caraslayer.setBackgroundResource(R.drawable.doomguyface)
                abrirMain(user)
            }

            user == "Demonio" && pass == "asdfasdf" -> {
                Toast.makeText(this, "Ha iniciado sesion como $user", Toast.LENGTH_SHORT).show()
                binding.caraslayer.setBackgroundResource(R.drawable.facedead)
                abrirMain(user)
            }

            else -> {
                builder.setTitle("El usuario o la contraseña son incorrectos")
                builder.setNeutralButton("Ok") { dialog, which -> }
                val dialog = builder.create()
                dialog.show()
            }

        }
    }

    //Esta funcion guarda en prefs el usuario si no está vacío y abre la siguiente actividad
    private  fun abrirMain(user: String){
        if(user.isNotEmpty()){
            guardarpref(user)
        }
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)

        finish()
    }

    //Esta funcion guarda en prefs el user, independientemente si está o no vacio
    private fun guardarpref(user: String){
        val preferencias = getSharedPreferences("pref", MODE_PRIVATE)
        val editor =preferencias.edit()
        editor.putString("user",user)
        editor.apply()
    }
}