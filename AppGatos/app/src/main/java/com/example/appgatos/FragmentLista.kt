package com.example.appgatos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.appgatos.databinding.ListaFragmentBinding
import com.example.appgatos.dataclass.Gato
import com.example.appgatos.retrofit.Repositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentLista : Fragment() {

    private lateinit var binding: ListaFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = ListaFragmentBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = "Gaticos y sus razas"

        val repo = Repositorio()

        CoroutineScope(Dispatchers.IO).launch {
            val gatos = repo.todosLosGatos()

            withContext(Dispatchers.Main){
                if(gatos.isSuccessful){
                    val listGatos = gatos.body()

                    listGatos?.let { configRecycler(listGatos) }
                }
            }

        }

    }

    private fun configRecycler(listGato: List<Gato>){
        val reciclerView = binding.recicler
        val adapter = gatoAdapter(listGato)
        val layoutManager =  LinearLayoutManager(reciclerView.context)
        reciclerView.layoutManager = layoutManager
        reciclerView.adapter = adapter
    }


}