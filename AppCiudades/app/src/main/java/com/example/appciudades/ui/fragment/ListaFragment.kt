package com.example.appciudades.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
            if(vm.todoCerveza.value!!.isEmpty()){
                Toast.makeText(requireContext(), "Mi loco no tienes nada que borrar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("¿Estás seguro de borrar todas las cervezas?")
            builder.setPositiveButton("Si") { dialog, which ->
                pruebaBeer(vm)
            }
            builder.setNegativeButton("No") { dialog, which ->
                Toast.makeText(context, "Pues nada", Toast.LENGTH_SHORT).show()
            }
            val dialog = builder.create()
            dialog.show()

        }

        binding.beer.setOnClickListener {
            nav.navigate(R.id.action_listaFragment_to_newbeer)
        }

        binding.mapa.setOnClickListener {
            nav.navigate(R.id.action_listaFragment_to_mapsFragment)
        }


        //Cuando se hace scroll en el recicler el fab se oculta, en cambio cuando se detiene se muestra
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 || dy < 0) {
                    binding.mapa.hide()
                    binding.beer.hide()
                    binding.fabPrueba.hide()
                }
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                binding.mapa.show()
                binding.beer.show()
                binding.fabPrueba.show()
            }
        })

        configRecicler()



        vm.todoCerveza.observe(viewLifecycleOwner){
            adapter.updateList(it)
            binding.CerCont.text = adapter.itemCount.toString()

        }


    }

    private fun pruebaBeer(vm: MyVM){
        vm.eliminarAllCerveza()
    }

    private fun configRecicler(){
        adapter = ListaBeerAdapter(requireContext(),servesa)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

}