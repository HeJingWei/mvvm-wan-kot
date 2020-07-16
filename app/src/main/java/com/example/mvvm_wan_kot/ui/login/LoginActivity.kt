package com.example.mvvm_wan_kot.ui.login

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.NavIconType
import com.example.mvvm_wan_kot.common.base.BaseVMActivity
import com.example.mvvm_wan_kot.common.ext.setOnClickListener
import com.example.mvvm_wan_kot.common.ext.setToolbar
import com.example.mvvm_wan_kot.common.ext.showToast
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginActivity : BaseVMActivity<LoginViewModel>() {


    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun initVM(): LoginViewModel {
        return getViewModel()
    }

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm, mViewModel)
        }
        setToolbar(getString(R.string.login), NavIconType.BACK)
        setOnClickListener(login) {
            mViewModel.login()
        }
    }

    override fun initData() {

    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(this@LoginActivity, Observer {
                if (it.isShowLoading) showProgressDialog(R.string.login_ing) else hideProgressDialog()

                if (it.isSuccess) {
                    showToast(R.string.login_success)
                    finish()
                }

            })
        }
    }

}