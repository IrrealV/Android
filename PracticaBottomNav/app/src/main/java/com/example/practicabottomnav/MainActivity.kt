package com.example.practicabottomnav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.practicabottomnav.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.ContainerView)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.listaFragment,
                R.id.webFragment,
                R.id.infoFragment,

            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navegacionInferior.setupWithNavController(navController)
    }
}