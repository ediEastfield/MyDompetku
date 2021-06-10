package com.dicoding.mydompetku.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dicoding.mydompetku.home.lastMonth.LastMonthFragment
import com.dicoding.mydompetku.home.thisMonth.ThisMonthFragment

class SectionsHomePagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = ThisMonthFragment()
            1 -> fragment = LastMonthFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 2
}