package com.example.mvvm_wan_kot.ui.main.system

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.NavIconType
import com.example.mvvm_wan_kot.common.base.BaseActivity
import com.example.mvvm_wan_kot.common.ext.setToolbar
import com.example.mvvm_wan_kot.model.bean.SystemBean
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_sys.*

class SystemListActivity : BaseActivity() {
    override fun getLayoutResId() = R.layout.activity_sys

    companion object {
        const val SYS_BEAN = "sys_bean"
    }

    private val sysBean by lazy {
        intent.getSerializableExtra(SYS_BEAN) as SystemBean
    }

    override fun initView() {
        setToolbar(sysBean.name, NavIconType.BACK)
        initViewPager()
    }

    override fun initData() {
    }

    private fun initViewPager() {
        systemDetailViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = sysBean.children.size

            override fun createFragment(position: Int) =
                SystemTypeFragment.newInstance(sysBean.children[position].id)
        }

        TabLayoutMediator(homeTabLayout, systemDetailViewPager) { tab, position ->
            tab.text = sysBean.children[position].name
        }.attach()
    }
}