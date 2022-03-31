package com.example.kcount

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kcount.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerlayout
        val navView: NavigationView = binding.navigationview
        val navController = findNavController(R.id.fragmentContainerView)


        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainFragment, R.id.contacFragment, R.id.galeriaFragment, R.id.demoniosFragment
            ), drawerLayout
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)




    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }



    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topmenu, menu)
        val usuario = findViewById<TextView>(R.id.usuario)
        val fecha = findViewById<TextView>(R.id.fecha)
        val bundle = intent.extras
        val user = bundle?.getString("user")
        val fechaHoy = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy/MM/dd HH-mm:ss", Locale.getDefault())
        val textoFecha = sdf.format(fechaHoy.time)
        fecha.text = textoFecha

        if(user!!.isNotEmpty()){
            usuario.text = user
        }




        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val builder = AlertDialog.Builder(this)


        return when (item.itemId) {
            R.id.cerrarses -> {
                builder.setTitle("¿Cerrar sesión?")

                builder.setPositiveButton("Si") { dialog, which ->
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                builder.setNegativeButton("No") { dialog, which ->

                }

                val dialog = builder.create()
                dialog.show()

                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}