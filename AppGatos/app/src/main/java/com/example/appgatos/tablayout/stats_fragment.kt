package com.example.appgatos.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appgatos.databinding.VistaStatsBinding
import com.example.appgatos.dataclass.Gato


class stats_fragment : Fragment() {
    private lateinit var binding: VistaStatsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VistaStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val  gato : Gato? = arguments?.getParcelable("gato")

        binding.Adapt.text = gato?.adaptability.toString()
        binding.Affect.text = gato?.affectionLevel.toString()
        binding.Intel.text = gato?.Intelligence.toString()
        binding.Social.text = gato?.socialNeeds.toString()
        binding.chld.text = gato?.childFriendly.toString()
        binding.dog.text = gato?.dogFriendly.toString()
        binding.health.text = gato?.healthIssues.toString()
        binding.shedding.text = gato?.sheddingLevel.toString()
        binding.Intel.text = gato?.Intelligence.toString()
        binding.Social.text = gato?.socialNeeds.toString()
        binding.energy.text = gato?.energyLevel.toString()

    }
}