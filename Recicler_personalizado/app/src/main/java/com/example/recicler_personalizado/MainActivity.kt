package com.example.recicler_personalizado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recicler_personalizado.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)




    }
}