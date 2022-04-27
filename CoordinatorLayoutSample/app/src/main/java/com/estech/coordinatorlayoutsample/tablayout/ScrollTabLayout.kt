package com.estech.coordinatorlayoutsample.tablayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.estech.coordinatorlayoutsample.R
import com.estech.coordinatorlayoutsample.databinding.TablayoutActivityBinding
import com.google.android.material.tabs.TabLayout


/**
 * Created by sergi on 26/04/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class ScrollTabLayout : AppCompatActivity() {

    private lateinit var binding: TablayoutActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TablayoutActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        title = "Toolbar Scroll"

        binding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val id = tab!!.position
                navigateToFragment(id)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        navigateToFragment(0)
    }

    private fun navigateToFragment(itemId: Int) {
        //fragment y titulo por defecto
        val fragment: Fragment
        fragment = when (itemId) {
            1 -> Tab2Fragment()
            2 -> Tab3Fragment()
            else -> Tab1Fragment()
        }

        // transacción del fragment al framelayout
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.framelayout, fragment)
        transaction.commit()
    }
}