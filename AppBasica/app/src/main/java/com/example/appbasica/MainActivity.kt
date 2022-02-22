package com.example.appbasica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
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
        var dinconv = findViewById<RadioGroup>(R.id.Conversor)
        var dolar = findViewById<RadioButton>(R.id.dolar)
        var euro = findViewById<RadioButton>(R.id.euros)
        var conver = findViewById<Button>(R.id.conversor)
        val reset = findViewById<Button>(R.id.reinicio)
        val fondres = findViewById<ImageView>(R.id.fondoresultado)
        var resconv = findViewById<TextView>(R.id.resultadoconver)

        //
        cambiarTema.setOnCheckedChangeListener { compoundButton, cheked ->
            if(cheked){
                val drawable = ContextCompat.getDrawable(this, R.drawable.fondoresultadonegro)
                fondres.background = drawable
            }else{
                val drawable = ContextCompat.getDrawable(this, R.drawable.fondoresultado)
                fondres.setBackgroundResource(R.drawable.fondoresultado)
            }
        }

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
                ressum.visibility = View.INVISIBLE
            }
            else{
                ressum.visibility = View.VISIBLE
            }
        }

        /*conver.setOnClickListener {
            val valorString = valorConver.text.toString()

            if (valorString.isEmpty()){
                Toast.makeText(this, "El campo está vacio", Toast.LENGTH_SHORT).show()
            }else{
                val valor = valorString.toDouble()
                if(dinconv.checkedRadioButtonId == dolar.isChecked){}
            }



        }*/



    }
}