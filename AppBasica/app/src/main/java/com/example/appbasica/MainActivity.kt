package com.example.appbasica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.awt.font.TextAttribute

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonsum1 = findViewById<Button>(R.id.mas1)
        val botonmen1 = findViewById<Button>(R.id.menos1)
        var chngnum = findViewById<EditText>(R.id.chngnum)
        var ressum = findViewById<TextView>(R.id.resultadosuma)
        var ocultar = findViewById<Switch>(R.id.ocultar)
        val cambiarTema = findViewById<ToggleButton>(R.id.cambiartema)
        var valorConver = findViewById<EditText>(R.id.valor_convertir)
        var dolar = findViewById<RadioButton>(R.id.dolar)
        var euro = findViewById<RadioButton>(R.id.euros)
        val conver = findViewById<Button>(R.id.conversor)
        val reset = findViewById<Button>(R.id.reinicio)
        var resconv = findViewById<TextView>(R.id.resultadoconver)

        //Esto hace que se cree una ventana emergente diciendo la operacion que se hizo
        botonsum1.setOnClickListener{
            Toast.makeText( this ,"Se ha sumado uno", Toast.LENGTH_SHORT).show()
        }
        botonmen1.setOnClickListener{
            Toast.makeText( this ,"Se ha restado uno", Toast.LENGTH_SHORT).show()
        }

        //Esto hace que el resultado se oculte cuando el switch es activado
        ocultar.setOnCheckedChangeListener {_, isChecked ->
            if (isChecked){
                ressum.visibility = View.GONE
            }
            else{
                ressum.visibility = View.VISIBLE
            }
        }

        //Esto hace que al introducir un numero los botenes sumen o resten uno a ese numero y lo pongan en el lugar del resultado
        botonsum1.setOnClickListener {
            chngnum
        }


    }
}