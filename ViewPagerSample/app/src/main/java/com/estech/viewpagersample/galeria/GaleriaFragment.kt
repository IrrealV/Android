package com.estech.viewpagersample.galeria

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.estech.viewpagersample.R
import com.estech.viewpagersample.databinding.FragmentGaleriaBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GaleriaFragment : Fragment() {

    private lateinit var binding: FragmentGaleriaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentGaleriaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listaPeliculas =
            listOf(R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4)
        val adapter = AdapterGallery(listaPeliculas)
        binding.viewpager.adapter = adapter

        binding.buttonFinal.setOnClickListener {
            val tamanioLista = listaPeliculas.size
            binding.viewpager.currentItem = tamanioLista - 1
        }

        binding.imageviewBack.setOnClickListener {
            val position = binding.viewpager.currentItem
            binding.viewpager.currentItem = position - 1
        }

        binding.imageviewNext.setOnClickListener {
            val position = binding.viewpager.currentItem
            binding.viewpager.currentItem = position + 1
        }

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Toast.makeText(requireContext(), "Página ${position + 1}", Toast.LENGTH_SHORT).show()
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }
}