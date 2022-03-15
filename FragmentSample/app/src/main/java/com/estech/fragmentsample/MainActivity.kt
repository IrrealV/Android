package com.estech.fragmentsample


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.estech.fragmentsample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val objetoFragment = BlankFragment() // creamos un objeto de nuestro Fragment
        val gestorDeFragment = supportFragmentManager // esta propiedad se encarga de gestionar los frament
        val transaccion = gestorDeFragment.beginTransaction() // le decimos que queremos realizar una transacción
        transaccion.add(R.id.container, objetoFragment) //con la funcion add, indicamos el container y fragment a mostrar
        transaccion.commit() // la función commit realiza la transacción

//        binding.floatingActionButton.setOnClickListener {
//            val fragment2 = BlankFragment2.newInstance("sergio", "velasco")
//            val transaction = supportFragmentManager.beginTransaction().apply {
//                replace(R.id.container, fragment2)
//                addToBackStack(null)
//            }
//            transaction.commit()
//        }
    }


}