package com.example.appgatos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.appgatos.databinding.FragmentVotoBinding
import com.example.appgatos.retrofit.Repositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class fragmentVoto : Fragment() {
    private lateinit var binding: FragmentVotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nav = findNavController()

        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

        binding.toolbar.setOnClickListener {
            nav.navigate(R.id.action_fragmentVoto_to_fragmentLista)

        }

        }
}