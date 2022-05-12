package com.estech.gatosmvvm

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.estech.gatosmvvm.adapters.BreedsAdapter
import com.estech.gatosmvvm.databinding.FragmentListaGatosBinding
import com.estech.gatosmvvm.viewmodels.GatoViewModel
import com.estech.retrofitsample.retrofit.Repositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListaGatosFragment : Fragment() {

    private lateinit var binding: FragmentListaGatosBinding
    private lateinit var adapter: BreedsAdapter
    private val gatoVm : GatoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentListaGatosBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).changeToolbar(binding.toolbar, false)
        (requireActivity() as MainActivity).changeToolbarTitle("Razas de Gato")

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecycler()

        gatoVm.listaRazas.observe(viewLifecycleOwner){
            adapter.actualizaLista(it)
        }

        gatoVm.errorRazas.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.swipe.setColorSchemeColors(Color.GREEN, Color.YELLOW)
        binding.swipe.setProgressBackgroundColorSchemeColor(Color.DKGRAY)
        binding.swipe.setSize(SwipeRefreshLayout.LARGE)
        binding.swipe.setOnRefreshListener {
            gatoVm.getListRazas()
        }

        gatoVm.getListRazas()

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_listaVotosFragment)
        }

    }

//    private fun getListaGatos() {
//        binding.swipe.isRefreshing = true
//        val miRepositorio = Repositorio()
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = miRepositorio.getRazas()
//
//            withContext(Dispatchers.Main) {
//                binding.swipe.isRefreshing = false
//                if (response.isSuccessful) {
//                    val respuesta = response.body()
//                    respuesta?.let {
//                        adapter.actualizaLista(respuesta)
//                    }
//                } else {
//                    Toast.makeText(
//                        requireContext(),
//                        "Error: ${response.message()}",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//    }

    private fun configRecycler() {

        val recyclerView = binding.recyclerview
        adapter = BreedsAdapter(object : BreedsAdapter.RazaClickListener)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_down -> {
                adapter.cambiarOrden(false)
                true
            }
            R.id.menu_up -> {
                adapter.cambiarOrden(true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}