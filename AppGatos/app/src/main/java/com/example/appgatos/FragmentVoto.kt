package com.example.appgatos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appgatos.adapter.VotoAdapter
import com.example.appgatos.databinding.FragmentVotoBinding
import com.example.appgatos.dataclass.Voto
import com.example.appgatos.retrofit.Repositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentVoto : Fragment() {
    private lateinit var binding: FragmentVotoBinding
    private lateinit var adapter : VotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = findNavController()

        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        binding.toolbar.setOnClickListener {
            nav.navigate(R.id.fragmentLista)

        }
        binding.toolbar.title = "Gaticos Votados"



        val repo = Repositorio()
        CoroutineScope(Dispatchers.IO).launch {
            val votos = repo.todosLosVotos()

            withContext(Dispatchers.Main){
                if(votos.isSuccessful){
                    val listVOTOS = votos.body()
                    listVOTOS?.let { configRecycler(listVOTOS) }
                }
            }

        }

    }

    private fun configRecycler(listVoto: List<Voto>){
        val reciclerView = binding.reciclerview
        adapter = VotoAdapter(listVoto as ArrayList<Voto>, requireContext())
        val layoutManager =  LinearLayoutManager(reciclerView.context)
        reciclerView.layoutManager = layoutManager
        reciclerView.adapter = adapter
    }
}