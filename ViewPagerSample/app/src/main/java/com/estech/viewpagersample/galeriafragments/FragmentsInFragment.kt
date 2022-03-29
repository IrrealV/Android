package com.estech.viewpagersample.galeriafragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.estech.viewpagersample.databinding.FragmentWithFragmentsBinding
import com.estech.viewpagersample.galeriafragments.fragments.Tutorial1
import com.estech.viewpagersample.galeriafragments.fragments.Tutorial2
import com.estech.viewpagersample.galeriafragments.fragments.Tutorial3

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FragmentsInFragment : Fragment() {

    private lateinit var binding: FragmentWithFragmentsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentWithFragmentsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listaFragment = listOf(Tutorial1(), Tutorial2(), Tutorial3())
        val adapter = TutorialAdapter(this, listaFragment)
        binding.viewpager.adapter = adapter

        binding.btBack.setOnClickListener {
            val position = binding.viewpager.currentItem
            if (position > 0)
                binding.viewpager.currentItem = position - 1
        }

        binding.btFinish.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btForward.setOnClickListener {
            val position = binding.viewpager.currentItem
            if (position < (listaFragment.size - 1))
                binding.viewpager.currentItem = position + 1
        }

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val numItems = listaFragment.size - 1

                when (position) {
                    0 -> {
                        binding.btBack.visibility = View.INVISIBLE
                        binding.btFinish.visibility = View.INVISIBLE
                        binding.btForward.visibility = View.VISIBLE
                    }
                    numItems -> {
                        binding.btBack.visibility = View.VISIBLE
                        binding.btFinish.visibility = View.VISIBLE
                        binding.btForward.visibility = View.INVISIBLE
                    }
                    else -> {
                        binding.btBack.visibility = View.VISIBLE
                        binding.btFinish.visibility = View.INVISIBLE
                        binding.btForward.visibility = View.VISIBLE
                    }
                }

            }
        })
    }
}