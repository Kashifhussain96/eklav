package com.mytvapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


 class FragmentAdapter(
    fm: FragmentManager?,
    var mNumOfTabs: Int,
    private var data: MutableList<String>?
) :
    FragmentStatePagerAdapter(fm!!) {
     override fun getItem(position: Int): Fragment {
        return ListFragment.newInstance(data!![position], "")
    }

    override fun getCount(): Int {
        return mNumOfTabs
    }

 }