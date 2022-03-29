package com.estech.viewpagersample.tutorialactivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.estech.viewpagersample.galeriafragments.fragments.Tutorial1
import com.estech.viewpagersample.galeriafragments.fragments.Tutorial2
import com.estech.viewpagersample.galeriafragments.fragments.Tutorial3


/**
 * Created by sergi on 22/03/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class TutorialAdapter2(val activity: FragmentActivity, val listaFragment: List<Fragment>): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return listaFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> Tutorial2()
            2 -> Tutorial3()
            else -> Tutorial1()
        }
    }
}