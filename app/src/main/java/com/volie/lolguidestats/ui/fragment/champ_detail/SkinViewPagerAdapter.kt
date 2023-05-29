package com.volie.lolguidestats.ui.fragment.champ_detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SkinViewPagerAdapter(
    private val pages: List<Fragment>,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment = pages[position]

}