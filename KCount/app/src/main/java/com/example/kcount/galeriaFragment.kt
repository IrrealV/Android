package com.example.kcount

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.estech.viewpagersample.galeria.AdapterGaleria
import com.example.kcount.databinding.FragmentGaleriaBinding


class galeriaFragment : Fragment() {

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

        val listafotos =
            listOf(R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4,R.drawable.img_5)
        val adapter = AdapterGaleria(listafotos)
        binding.viewpager.adapter = adapter

        binding.buttonFinal.setOnClickListener {
            val tamanioLista = listafotos.size
            binding.viewpager.currentItem = tamanioLista - 1
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

            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })
    }
}
