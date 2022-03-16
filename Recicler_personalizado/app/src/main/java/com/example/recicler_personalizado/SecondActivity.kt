package com.example.recicler_personalizado

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.inputmethod.InputBinding
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.recicler_personalizado.databinding.ActivityMainBinding
import com.example.recicler_personalizado.databinding.ActivitySecondBinding

class SecondActivity(): AppCompatActivity() {


    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.senddata.setOnClickListener {
            when{
                binding.name.text?.isEmpty() == true->
                    binding.name.error = "Campo vacío"
                binding.age.text?.isEmpty()== true ->
                    binding.age.error = "Campo vacío"
                binding.ability.text?.isEmpty()== true->
                    binding.ability.error = "Campo vacío"
                else->{
                    val intent = Intent()
                    intent.putExtra("nombre", binding.name.text.toString())
                    intent.putExtra("edad", binding.age.text.toString())
                    intent.putExtra("habilidad",binding.ability.text.toString())
                    setResult(3, intent)
                    finish()
                }
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                false
            }
        }
    }
}