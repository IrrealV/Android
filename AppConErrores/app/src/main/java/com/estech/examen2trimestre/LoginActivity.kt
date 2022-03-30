package com.estech.examen2trimestre

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.estech.examen2trimestre.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText


/**
 * Created by Sergio on 28/03/2022.
 */

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // se definen las vistas

        // click boton entrar
        binding.loginBtnLogin.setOnClickListener {
            val usertext = binding.loginEdUser.text.toString()
            val passtext = binding.loginEdPass.toString()
            compruebaLogin(usertext, passtext)
        }

        //todo autologin para testear
        //abrirMainActivity()
    }

    /**
     * Función que comprueba si el usuario es correcto o no
     * Recibe como parámetros el usuario y la contraseña como texto
     * Si no hay errores, llama a la función abrirMainActivity
     */
    private fun compruebaLogin(user: String, pass: String) {

        if (user.isEmpty()) {
            binding.loginEdUser.error = "El campo está vacío"
            return
        }
        if (pass.isEmpty()) {
            binding.loginEdPass.error = "El campo está vacío"
            return
        }

        if (pass != "12345") {
            binding.loginEdPass.error = "Contraseña incorrecta"
            return
        }

        abrirMainActivity()
    }

    /**
     * Función que abre la actividad MainActivity cuando es llamada
     */
    private fun abrirMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}