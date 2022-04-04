package com.example.kcount

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.kcount.databinding.ActivityMainBinding
import com.example.kcount.databinding.CabeceraBinding
import com.example.kcount.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = requireContext().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val usuario = preferences.getString("user", "")
        val contador = preferences.edit()

        if(usuario!!.isNotEmpty()){
            if (usuario != "Demonio"){
                binding.bienvenida.text = "BIENVENIDO $usuario"
            }
            else{
                binding.bienvenida.text = "Maldito seas $usuario"
            }
        }
        else{
            binding.bienvenida.text = ""
        }

        binding.slayer.setOnClickListener {
            Toast.makeText(requireContext(),"Pulsó 10 veces", Toast.LENGTH_SHORT).show()
            var contExt = preferences.getInt("cuenta", 0)
            var contInt = 0
            if(contExt.toString().toInt() == 0){
                contInt += 1
                if (contInt == 10){

                    contador.putInt("cuenta",1)
                }
            }
        }
    }


}





