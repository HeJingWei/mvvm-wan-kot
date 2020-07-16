package com.example.mvvm_wan_kot.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabFragmentPagerAdapter : FragmentPagerAdapter {

    private var list:MutableList<Fragment>

    constructor(list:MutableList<Fragment>,fm: FragmentManager, behavior: Int) : super(fm, behavior){
        this.list = list
    }

    override fun getItem(position: Int) = list[position]

    override fun getCount(): Int =list.size


}