package com.estech.gatosmvvm.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.estech.gatosmvvm.MainActivity
import com.estech.gatosmvvm.adapters.ViewPagerAdapter
import com.estech.gatosmvvm.databinding.FragmentDetalleGatoBinding
import com.estech.gatosmvvm.modelos.enviarvoto.SendVote
import com.estech.gatosmvvm.modelos.listagatos.Breed
import com.estech.retrofitsample.retrofit.Repositorio
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GatoDetalleFragment : Fragment() {

    private lateinit var binding: FragmentDetalleGatoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetalleGatoBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).changeToolbar(binding.toolbar, true)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val breed: Breed? = arguments?.getParcelable("raza")
        breed.let { raza ->

            (requireActivity() as MainActivity).changeToolbarTitle(raza!!.name!!)

            if (raza.image != null && raza.image.url != null) {
                Glide.with(this).load(raza.image.url).into(binding.catImg)
            }

            binding.megusta.setOnClickListener { votaGato(1, raza.image?.id!!) }

            binding.nomegusta.setOnClickListener { votaGato(0, raza.image?.id!!) }

            binding.fab.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(raza.wikipediaUrl)))
            }

            setupViewPager(raza)
        }
    }

    private fun setupViewPager(raza: Breed) {
        val adapter = ViewPagerAdapter(this, raza)
        binding.materialupViewpager.adapter = adapter
        val mediator = TabLayoutMediator(
            binding.tabs, binding.materialupViewpager
        ) { tab, position ->
            if (position == 0) {
                tab.text = "Information"
            } else {
                tab.text = "Stats"
            }
        }
        mediator.attach()
    }

    private fun votaGato(voto: Int, imageId: String) {
        val miRepositorio = Repositorio()
        val sendVote = SendVote(imageId, voto, "Sergio")

        CoroutineScope(Dispatchers.IO).launch {
            val response = miRepositorio.sendVote(sendVote)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val respuesta = response.body()
                    respuesta?.let {
                        val message = it.message
                        Toast.makeText(requireContext(), "Resultado: $message", Toast.LENGTH_SHORT)
                            .show()
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