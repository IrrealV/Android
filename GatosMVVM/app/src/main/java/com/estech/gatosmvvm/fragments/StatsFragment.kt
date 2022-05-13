package com.estech.gatosmvvm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.estech.gatosmvvm.databinding.FragmentStatsBinding
import com.estech.gatosmvvm.modelos.listagatos.Breed
import com.estech.gatosmvvm.viewmodels.GatoViewModel

class StatsFragment : Fragment() {

    private lateinit var binding: FragmentStatsBinding
    private val gatosVm : GatoViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gatosVm.razaSeleccionada.observe(viewLifecycleOwner) {
            binding.textFragment1.text = "Adaptability: ${it!!.adaptability}"
            binding.textFragment2.text = "Affection Level: ${it.affectionLevel}"
            binding.textFragment3.text = "Child Friendly: ${it.childFriendly}"
            binding.textFragment4.text = "Dog Friendly: ${it.dogFriendly}"
            binding.textFragment5.text = "Energy Level: ${it.energyLevel}"
            binding.textFragment6.text = "Health Issues: ${it.healthIssues}"
            binding.textFragment7.text = "Shedding Level: ${it.sheddingLevel}"
            binding.textFragment8.text = "Intelligence: ${it.intelligence}"
            binding.textFragment9.text = "Social Needs: ${it.socialNeeds }"
        }
    }
}