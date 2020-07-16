package com.example.mvvm_wan_kot.ui.login.register

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.NavIconType
import com.example.mvvm_wan_kot.common.base.BaseVMActivity
import com.example.mvvm_wan_kot.common.ext.setToolbar
import com.example.mvvm_wan_kot.common.ext.showToast
import org.koin.androidx.viewmodel.ext.android.getViewModel

class RegisterActivity : BaseVMActivity<RegisterViewModel>() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_register
    }

    override fun initVM(): RegisterViewModel {
        return getViewModel()
    }

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm,mViewModel)
        }
        setToolbar(getString(R.string.register),NavIconType.BACK)
    }

    override fun initData() {

    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(this@RegisterActivity, Observer {
                if (it.isShowing) showProgressDialog(R.string.register_ing) else hideProgressDialog()
                if (it.isSuccess){
                    showToast(R.string.register_success)
                    finish()
                }
            })
        }
    }
}