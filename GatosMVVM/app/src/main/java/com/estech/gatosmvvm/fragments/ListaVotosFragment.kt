package com.estech.gatosmvvm.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.estech.gatosmvvm.MainActivity
import com.estech.gatosmvvm.R
import com.estech.gatosmvvm.adapters.VotesAdapter
import com.estech.gatosmvvm.databinding.FragmentVoteListBinding
import com.estech.gatosmvvm.modelos.listavotos.Votes
import com.estech.gatosmvvm.retrofit.Repositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Created by sergi on 11/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class ListaVotosFragment : Fragment() {

    private lateinit var binding: FragmentVoteListBinding
    private lateinit var adapter: VotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentVoteListBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).changeToolbar(binding.toolbar, true)
        (requireActivity() as MainActivity).changeToolbarTitle("Mis votos")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecycler()

        binding.swipe.setColorSchemeColors(Color.MAGENTA, Color.YELLOW)
        binding.swipe.setColorSchemeResources(R.color.purple_200, R.color.teal_200)
        binding.swipe.setProgressBackgroundColorSchemeColor(Color.DKGRAY)
        binding.swipe.setSize(SwipeRefreshLayout.LARGE)
        binding.swipe.setOnRefreshListener {
            getListaVotos()
        }

        getListaVotos()

    }

    private fun getListaVotos() {
        binding.swipe.isRefreshing = true
        val miRepositorio = Repositorio()

        CoroutineScope(Dispatchers.IO).launch {
            val response = miRepositorio.getVotesList("Sergio")

            withContext(Dispatchers.Main) {
                binding.swipe.isRefreshing = false
                if (response.isSuccessful) {
                    val respuesta = response.body()
                    respuesta?.let {
                        adapter.actualizaLista(respuesta)
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Error: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun configRecycler() {

        val recyclerView = binding.votelistRv
        adapter = VotesAdapter(object : VotesAdapter.EliminarVotoClickListener {
            override fun onClick(voto: Votes) {
                deleteVoto(voto.id.toString())
            }

            override fun onLongClick() {

            }

        })
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun deleteVoto(id: String) {
        binding.swipe.isRefreshing = true
        val miRepositorio = Repositorio()

        CoroutineScope(Dispatchers.IO).launch {
            val response = miRepositorio.deleteVote(id)

            withContext(Dispatchers.Main) {
                binding.swipe.isRefreshing = false
                if (response.isSuccessful) {
                    val respuesta = response.body()
                    respuesta?.let {
                        if (respuesta.message == "SUCCESS")
                            getListaVotos()
                        else
                            Toast.makeText(
                                requireContext(),
                                "Error al eliminar el voto",
                                Toast.LENGTH_SHORT
                            ).show()
                    }
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Error: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}