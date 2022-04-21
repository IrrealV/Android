package com.example.appvalorant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.appvalorant.databinding.FragmentPersonajeBinding
import com.example.appvalorant.modelos.Personaje


class personajeFragment : Fragment() {

    private lateinit var binding: FragmentPersonajeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonajeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val personaje: Personaje? = arguments?.getParcelable("personaje")
        if (personaje != null) {
            (requireActivity() as MainActivity).supportActionBar?.title = personaje.nombre
            binding.tvName.text = personaje.nombre
            Glide.with(this).load(personaje.imagen).into(binding.ivImage)
            Glide.with(this).load(personaje.fondo).into(binding.imageView)
        }
    }
}