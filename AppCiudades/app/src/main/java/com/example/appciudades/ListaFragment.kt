package com.example.appciudades

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appciudades.MyBeer
import com.example.appciudades.databinding.FragmentListaBinding
import com.example.appciudades.dominio.models.Cerveza
import com.example.appciudades.ui.adapter.ListaBeerAdapter
import com.example.appciudades.viewModel.MyVM
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.maps.model.LatLng

class ListaFragment : Fragment() {

    private lateinit var binding: FragmentListaBinding
    private lateinit var adapter: ListaBeerAdapter
    private lateinit var servesa: ArrayList<Cerveza>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaBinding.inflate(inflater,container,false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        servesa = ArrayList()
        if(servesa.size > 0){
            binding.CerCont.text = servesa.size.toString()
        }
        binding.toolbar.title = "Lista de Cervezas"

        val madrid = LatLng(40.47883646461693, -3.7692950517063832)

        val myBeer = requireActivity().application as MyBeer
        val vm: MyVM by activityViewModels(){
            MyVM.MyViewModelFactory(myBeer.repositorio)
        }

        binding.beer.setOnClickListener {
            vm.insertarCerveza(
                Cerveza(
                    "Mahou",
                    "Linares",
                    "España",
                    17,
                    0,
                    0,
                    true,
                    madrid.toString()
                )
            )
        }


        //Cuando se hace scroll en el recicler el fab se oculta, en cambio cuando se detiene se muestra
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 || dy < 0) {
                    binding.map.hide()
                    binding.beer.hide()
                }
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                binding.map.show()
                binding.beer.hide()
            }
        })

        configRecicler()


        vm.todoCerveza.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }


    }



    private fun configRecicler(){
        adapter = ListaBeerAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

}