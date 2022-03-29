package com.estech.viewpagersample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.estech.viewpagersample.databinding.FragmentMainBinding
import com.estech.viewpagersample.tutorialactivity.TutorialActivity

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.botonGaleria.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_GaleriaFragment)
        }
        binding.botonLayout.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_avanzadaFragment)
        }
        binding.botonFragments.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_fragmentsInFragment)
        }
        binding.botonTutorial.setOnClickListener {
            startActivity(
                Intent(
                    requireContext() ,
                    TutorialActivity::class.java
                )
            )
        }
    }

}