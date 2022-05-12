package com.estech.gatosmvvm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.estech.gatosmvvm.databinding.FragmentStatsBinding
import com.estech.gatosmvvm.modelos.listagatos.Breed

private const val ARG_PARAM1 = "param1"

class StatsFragment : Fragment() {

    private var breed: Breed? = null
    private lateinit var binding: FragmentStatsBinding

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
        binding = FragmentStatsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Breed) =
            StatsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        breed.let {
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