package com.example.appvalorant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.appvalorant.databinding.ActivityMainBinding
import com.example.appvalorant.databinding.FragmentListaBinding
import com.example.appvalorant.modelos.Personaje
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream




class ListaFragment : Fragment() {

    private lateinit var binding: FragmentListaBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsonEnString = getJsonFromAsset2()
        if(!jsonEnString.isNullOrEmpty()){
            val gson = Gson()
            val  listaPersonaje = gson.fromJson(jsonEnString, Array<Personaje>::class.java).asList()
                configRecicler(listaPersonaje)
            }
    }

    private fun getJsonFromAsset2(): String? {
        var jsonString: String? = null
        try {
            val inputStream: InputStream = requireContext().assets.open("personajes.json")
            jsonString = inputStream.bufferedReader().use {
                it.readText()
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
        return jsonString
    }

    private fun configRecicler(listaPersonaje: List<Personaje>){
        val reciclerView = binding.recicler
        val adapter = personajeAdapter(listaPersonaje)
        val layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        reciclerView.layoutManager = layoutManager
        reciclerView.adapter = adapter
    }

}