package com.example.mvvm_wan_kot.common.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.mvvm_wan_kot.common.custom.ProgressDialogFragment
import com.example.mvvm_wan_kot.common.utils.ActivityManager
import com.example.mvvm_wan_kot.ui.login.LoginActivity

abstract class BaseVMActivity<VM : BaseViewModel>(useDataBinding: Boolean = true): AppCompatActivity() {


    private lateinit var progressDialogFragment: ProgressDialogFragment

    private val _useBinding = useDataBinding
    protected lateinit var mBinding: ViewDataBinding
    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = initVM()

        startObserve()
        if (_useBinding) {
            mBinding = DataBindingUtil.setContentView(this, getLayoutResId())
            mBinding.lifecycleOwner =this
        } else setContentView(getLayoutResId())
        initView()
        initData()
    }

    abstract fun initVM(): VM
    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
    open fun getLayoutResId(): Int = 0


    fun showProgressDialog(@StringRes message: Int = 0,isCancel : Boolean = true) {
        if (!this::progressDialogFragment.isInitialized) {
            progressDialogFragment = ProgressDialogFragment.newInstance()
        }
        progressDialogFragment.show(supportFragmentManager, message, isCancel)
    }

    fun hideProgressDialog() {
        if (this::progressDialogFragment.isInitialized && progressDialogFragment.isVisible) {
            progressDialogFragment.dismiss()
        }
    }

    /**
     * 是否登录，如果登录了就执行then，没有登录就直接跳转登录界面
     * @return true-已登录，false-未登录
     */
    fun checkLogin(then: (() -> Unit)? = null): Boolean {
        return if (BaseRepository().isLogin()) {
            then?.invoke()
            true
        } else {
            ActivityManager.start(LoginActivity::class.java)
            false
        }
    }
}