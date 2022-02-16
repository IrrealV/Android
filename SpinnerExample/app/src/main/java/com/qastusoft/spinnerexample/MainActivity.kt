package com.qastusoft.spinnerexample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //****************SPINNER POR XML*************************//
        val spinnerXml = findViewById<Spinner>(R.id.spinnerxml)
        val buttonXml = findViewById<Button>(R.id.buttonXml)

        val users = resources.getStringArray(R.array.lista)

        buttonXml.setOnClickListener {
            val item =
                spinnerXml.selectedItem.toString() //se obtiene el item seleccionado en el spinner
            Log.d("Spinner", item)

            val itemPosition = spinnerXml.selectedItemPosition //se obtiene la posicion seleccionada
            Log.d("Spinner", "Posición: $itemPosition")
        }

        spinnerXml.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@MainActivity, users[p2], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // funcion llamada cuando no se selecciona nada del spinner
            }

        }

        //****************SPINNER POR CÓDIGO*************************//
        val spinnerCode = findViewById<Spinner>(R.id.spinnercode)
        val buttonCode = findViewById<Button>(R.id.buttonCode)

        //adaptador desde un array que creamos por código
        val animales = arrayOf("Perro", "León", "Gato", "Lobo")
        val adapterAnimales = ArrayAdapter(this, android.R.layout.simple_spinner_item, animales)

        //adaptador desde unan lista mutable
        val vehiculos = mutableListOf("Coche, Moto, Avión")
        val adapterVehiculos = ArrayAdapter(this, android.R.layout.simple_spinner_item, vehiculos)

        //adaptador desde un array en strings.xml
        val usuarios = resources.getStringArray(R.array.lista)
        val adapterUsuarios = ArrayAdapter(this, android.R.layout.simple_spinner_item, usuarios)
        //val adapterUsuarios2 = ArrayAdapter(this, R.layout.otro_textview, R.id.textView3, usuarios)

        //otra forma desde un array de strings.xml
        val adapterPlanetas = ArrayAdapter.createFromResource(
            this,
            R.array.planetas,
            android.R.layout.simple_spinner_item
        )


        //asignar adapter al spinner
        spinnerCode.adapter = adapterPlanetas

        adapterPlanetas.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        spinnerCode.prompt = "Hola"

        buttonCode.setOnClickListener {
            val texto = spinnerCode.selectedItem.toString()
            val position = spinnerCode.selectedItemPosition

            Toast.makeText(this, "Seleccionado $texto con posición $position", Toast.LENGTH_SHORT)
                .show()
        }


        val floatingButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingButton.setOnClickListener {
            spinnerCode.setSelection(2)
        }
    }
}