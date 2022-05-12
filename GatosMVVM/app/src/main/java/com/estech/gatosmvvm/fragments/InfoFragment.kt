package com.estech.gatosmvvm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.estech.gatosmvvm.databinding.FragmentInfoBinding
import com.estech.gatosmvvm.modelos.listagatos.Breed

private const val ARG_PARAM1 = "param1"

class InfoFragment : Fragment() {

    private var breed: Breed? = null
    private lateinit var binding: FragmentInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            breed = it.getParcelable(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Breed) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        breed.let {
            binding.weight.text = "${it!!.weight?.metric} Kg"
            binding.temperament.text = it.temperament
            binding.origin.text = "${it.origin} ${it.countryCode}"
            binding.lifespan.text = it.lifeSpan
            binding.description.text = it.description
        }
    }
}