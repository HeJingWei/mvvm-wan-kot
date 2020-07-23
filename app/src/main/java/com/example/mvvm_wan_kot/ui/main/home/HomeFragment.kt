package com.example.mvvm_wan_kot.ui.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseFragment
import com.example.mvvm_wan_kot.ui.main.home.popular.PopularFragment
import com.example.mvvm_wan_kot.ui.main.home.project.ProjectFragment
import com.example.mvvm_wan_kot.ui.main.home.square.SquareFragment
import com.example.mvvm_wan_kot.ui.main.home.wxproject.WxProjectFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

//    private var tabTitles: List<String> = listOf("置顶", "文章", "广场", "项目", "公众号")
    private var tabTitles: List<String> = listOf("置顶", "广场","公众号", "项目")
    private val fragments by lazy {
        mutableListOf(PopularFragment(),SquareFragment(),WxProjectFragment(),ProjectFragment())
    }

    override fun getLayoutResId() = R.layout.fragment_home

    override fun initView() {
        viewPager.adapter = SimpleFragmentPagerAdapter(childFragmentManager, fragments, tabTitles)
        viewPager.offscreenPageLimit = fragments.size
        homeTabLayout.setupWithViewPager(viewPager)
        homeSearch.setOnClickListener {
            //跳转搜索页
        }
    }

    override fun initData() {

    }


    inner class SimpleFragmentPagerAdapter(
        fm: FragmentManager,
        private val fragments: List<Fragment>,
        private val titles: List<CharSequence>? = null
    ) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment = fragments[position]
        override fun getCount(): Int = fragments.size
        override fun getPageTitle(position: Int): CharSequence? = titles?.get(position)
        override fun getItemId(position: Int): Long = fragments[position].hashCode().toLong()
    }
}