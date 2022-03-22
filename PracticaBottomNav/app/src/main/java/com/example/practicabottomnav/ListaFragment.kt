package com.example.practicabottomnav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicabottomnav.adapter.MiAdaptador
import com.example.practicabottomnav.databinding.FragmentListaBinding
import com.example.practicabottomnav.databinding.FragmentWebBinding

class ListaFragment : Fragment() {
    lateinit var binding: FragmentListaBinding
    val leyendas = mutableListOf<Lista>()
    val adapter = MiAdaptador(leyendas)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentListaBinding.inflate(layoutInflater)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        val llm = LinearLayoutManager(context)
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter

        leyendas.add(Lista("Bangalore", 38, "Lanzahumo"))
        leyendas.add(Lista("Gibraltar", 30, "Cupula de protección"))
        leyendas.add(Lista("Ash", 121, "Trampa arrojadiza"))
        leyendas.add(Lista("Horizon", 125, "Ascensor gravitacional"))
        leyendas.add(Lista("Wattson", 22, "Perímetro de seguridad"))
        leyendas.add(Lista("Mirage", 30, "Desquiciar"))


    }
}