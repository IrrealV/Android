package com.example.appgatos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appgatos.adapter.GatoAdapter
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
        //Establece el titulo al string introducido
        binding.toolbar.title = "Gaticos y sus razas"

        val repo = Repositorio()
        val nav = findNavController()

        //Introduce la lista recibida de la api por el ceciler
        CoroutineScope(Dispatchers.IO).launch {
            val gatos = repo.todosLosGatos()

            withContext(Dispatchers.Main){
                if(gatos.isSuccessful){
                    val listGatos = gatos.body()
                    listGatos?.let { configRecycler(listGatos) }
                }
            }

        }

        binding.fab.setOnClickListener{
            nav.navigate(R.id.action_fragmentLista_to_fragmentVoto)
        }

        //Cuando se hace scroll en el recicler el fab se oculta, en cambio cuando se detiene se muestra
        binding.recicler.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 || dy < 0) {
                    binding.fab.hide()
                }
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                binding.fab.show()
            }
        })

    }

    private fun configRecycler(listGato: List<Gato>){
        val reciclerView = binding.recicler
        val adapter = GatoAdapter(listGato)
        val layoutManager =  LinearLayoutManager(reciclerView.context)
        reciclerView.layoutManager = layoutManager
        reciclerView.adapter = adapter
    }


}