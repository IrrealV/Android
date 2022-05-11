package com.estech.mvvmfragments.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.estech.mvvmfragments.R
import com.estech.mvvmfragments.databinding.FragmentListaBinding
import com.estech.mvvmfragments.ui.adapters.MyAdapter
import com.estech.mvvmfragments.viewmodel.JugadoresViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentListaBinding
    private lateinit var adapter: MyAdapter
    private val jugadoresViewModel: JugadoresViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecycler()

        binding.buttonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        jugadoresViewModel.listaJugadores.observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        jugadoresViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun configRecycler() {
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = MyAdapter()
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = adapter
    }
}