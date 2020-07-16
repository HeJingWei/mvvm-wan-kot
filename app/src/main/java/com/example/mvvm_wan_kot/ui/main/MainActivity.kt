package com.example.mvvm_wan_kot.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseActivity
import com.example.mvvm_wan_kot.common.ext.setOnClickListener
import com.example.mvvm_wan_kot.ui.main.adapter.TabFragmentPagerAdapter
import com.example.mvvm_wan_kot.ui.main.home.HomeFragment
import com.example.mvvm_wan_kot.ui.main.mine.MineFragment
import com.example.mvvm_wan_kot.ui.main.navigation.NavigationFragment
import com.example.mvvm_wan_kot.ui.main.system.SystemFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_bottom_navigation.*

class MainActivity : BaseActivity() {

    override fun getLayoutResId() = R.layout.activity_main

    private val fragmentManager: FragmentManager by lazy { supportFragmentManager }
    private val homeFragment by lazy {
        HomeFragment()
    }
    private val systemFragment by lazy {
        SystemFragment()
    }
    private val navigationFragment by lazy {
        NavigationFragment()
    }
    private val mineFragment by lazy {
        MineFragment()
    }
    private lateinit var tabFragmentPagerAdapter: TabFragmentPagerAdapter
    private var fragmentList = arrayListOf<Fragment>()
    private val iconList by lazy {
        mutableListOf(homeIcon, systemIcon, navigationIcon, mineIcon)
    }
    private val tabList by lazy {
        mutableListOf(homeTv, systemTv, navigationTv, mineTv)
    }

    init {
        fragmentList.add(homeFragment)
        fragmentList.add(systemFragment)
        fragmentList.add(navigationFragment)
        fragmentList.add(mineFragment)
    }

    override fun initView() {
        tabFragmentPagerAdapter = TabFragmentPagerAdapter(
            fragmentList, fragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        mainViewPager.adapter = tabFragmentPagerAdapter
        mainViewPager.offscreenPageLimit = 4
        selectFragmentItem(0)
        setOnClickListener(home, system, navigation, mine) {
            var position = 0;
            when (this){
                home -> {
                    position = 0;
                }
                system -> {
                    position = 1;
                }
                navigation -> {
                    position = 2;
                }
                mine -> {
                    position = 3;
                }
            }
            selectFragmentItem(position)
        }
    }

    override fun initData() {

    }

    private fun selectFragmentItem(position: Int) {
        iconList.forEach {
            it.setColorFilter(getColor(R.color.main_unselect_color))
        }
        tabList.forEach {
            it.setTextColor(getColor(R.color.main_unselect_color))
        }
        iconList[position].setColorFilter(getColor(R.color.main_select_color))
        tabList[position].setTextColor(getColor(R.color.main_select_color))
        mainViewPager.currentItem = position
    }
}

