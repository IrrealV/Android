package com.estech.viewpagersample.galeriafragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


/**
 * Created by sergi on 22/03/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class TutorialAdapter(val fragment: Fragment, val listaFragment: List<Fragment>): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return listaFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return listaFragment[position]
    }
}