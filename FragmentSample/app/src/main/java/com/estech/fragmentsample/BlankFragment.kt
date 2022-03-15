package com.estech.fragmentsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.estech.fragmentsample.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {

    private lateinit var binding: FragmentBlankBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = "Este es el fragment 1"
        binding.button.setOnClickListener {
//            val fragment2 = BlankFragment2.newInstance("Sergio", "Velasco")
//            val transaccion = parentFragmentManager.beginTransaction()
//            transaccion.replace(R.id.container, fragment2)
//            transaccion.commit()
        }
    }
}