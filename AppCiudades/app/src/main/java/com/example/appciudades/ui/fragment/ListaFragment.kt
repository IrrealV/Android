package com.example.appciudades.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appciudades.MyBeer
import com.example.appciudades.R
import com.example.appciudades.databinding.FragmentListaBinding
import com.example.appciudades.dominio.models.Cerveza
import com.example.appciudades.ui.adapter.ListaBeerAdapter
import com.example.appciudades.viewModel.MyVM
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
        binding.toolbar.title = "Lista de Cervezas"

        val myBeer = requireActivity().application as MyBeer
        val vm: MyVM by activityViewModels(){
            MyVM.MyViewModelFactory(myBeer.repositorio)
        }

        @SuppressLint("StaticFieldLeak")
        val nav = findNavController()


        binding.fabPrueba.setOnClickListener {
            pruebaBeer(vm)
        }

        binding.beer.setOnClickListener {
            nav.navigate(R.id.action_listaFragment_to_newbeer)
        }

        binding.map.setOnClickListener {
            nav.navigate(R.id.action_listaFragment_to_mapsFragment)
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
                binding.beer.show()
            }
        })

        configRecicler()



        vm.todoCerveza.observe(viewLifecycleOwner){
            adapter.updateList(it)
            binding.CerCont.text = adapter.itemCount.toString()

        }


    }

    private fun pruebaBeer(vm: MyVM){

        val madrid = LatLng(40.47883646461693, -3.7692950517063832)
        val Linares = LatLng(38.1737335959905, -3.7760733675386406)
                    vm.insertarCerveza(
                Cerveza(
                    "Mahou",
                    "Linares",
                    "España",
                    17,
                    madrid.latitude,
                    madrid.longitude,
                    true,
                    madrid.toString()
                )
            )
            vm.insertarCerveza(
                Cerveza(
                    "Castillo",
                    "Baños de la encina",
                    "España",
                    200,
                    Linares.latitude,
                    Linares.longitude,
                    true,
                    Linares.toString()
                )
            )
    }

    private fun configRecicler(){
        adapter = ListaBeerAdapter(requireContext())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

}