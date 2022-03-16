package com.example.recicler_personalizado

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.recicler_personalizado.databinding.ActivitySecondBinding
import com.example.recicler_personalizado.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.mail.setOnClickListener{
            val correo = Intent(Intent.ACTION_SENDTO)
            correo.data = Uri.parse("mailto:")
            correo.putExtra(Intent.EXTRA_EMAIL, arrayOf("vmherasdurillo@hotmail.com"))
            startActivity(correo)
        }

        binding.telf.setOnClickListener{
            val marca = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "658204691"))
            startActivity(marca)
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






