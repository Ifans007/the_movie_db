package com.example.themoviedb.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MovieViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var fragmentList : MutableList<Fragment> = arrayListOf()
    var fragmentTitleList : MutableList<String> = arrayListOf()

    fun addFragment(fragment:Fragment, title:String){
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitleList[position]
    }
}
