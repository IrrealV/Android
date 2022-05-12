package com.estech.gatosmvvm.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.estech.gatosmvvm.fragments.InfoFragment
import com.estech.gatosmvvm.fragments.StatsFragment
import com.estech.gatosmvvm.modelos.listagatos.Breed


/**
 * Created by sergi on 11/05/2022.
 * Copyright (c) 2022 Qastusoft. All rights reserved.
 */

class ViewPagerAdapter(fragment: Fragment, val raza: Breed) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            InfoFragment.newInstance(raza)
        else
            StatsFragment.newInstance(raza)
    }
}