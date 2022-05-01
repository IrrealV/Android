package com.example.appgatos


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.appgatos.databinding.FragmentGatoBinding
import com.example.appgatos.dataclass.Gato


class fragmentGato : Fragment() {

    private lateinit var binding : FragmentGatoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGatoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = findNavController()
        val gatico: Gato? =arguments?.getParcelable("gato")


        if (gatico != null) {
            binding.toolbar.title = gatico.name
            Glide.with(this).load(gatico.image?.url).into(binding.IgGto)
        }

        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

       binding.toolbar.setOnClickListener {
           nav.navigate(R.id.action_fragmentGato_to_fragmentLista)

       }




    }
}