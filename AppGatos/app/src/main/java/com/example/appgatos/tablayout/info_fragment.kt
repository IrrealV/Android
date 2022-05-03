package com.example.appgatos.tablayout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import com.example.appgatos.databinding.VistasInfoBinding
import com.example.appgatos.dataclass.Gato


class info_fragment : Fragment() {
    private lateinit var binding: VistasInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = VistasInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val  gato : Gato? = arguments?.getParcelable("gato")

        binding.peso.text = "${gato?.weight?.metric} kg"
        binding.comprt.text = gato?.temperament
        binding.origen.text = gato?.origin
        binding.espVida.text = "${gato?.lifeSpan} años"
        binding.desc.text = gato?.description






    }



}

