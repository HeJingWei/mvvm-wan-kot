package com.example.mvvm_wan_kot.ui.main.mine

import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseVMFragment
import com.example.mvvm_wan_kot.common.ext.setOnClickListener
import com.example.mvvm_wan_kot.common.utils.ActivityManager
import com.example.mvvm_wan_kot.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_mine.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MineFragment : BaseVMFragment<MineViewModel>() {

    override fun getLayoutResId() = R.layout.fragment_mine

    override fun initVM(): MineViewModel  = getViewModel()

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm,mViewModel)
        }

        setOnClickListener(homeMineName){
            ActivityManager.start(LoginActivity::class.java)
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.idIsVisible()
    }

    override fun initData() {

    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(viewLifecycleOwner, Observer {
                if (it.isLogin) homeMineId.visibility = View.VISIBLE else homeMineId.visibility = View.GONE
                homeMineName.isEnabled = !it.isLogin
            })
        }
    }
}