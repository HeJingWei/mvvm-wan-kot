package com.example.mvvm_wan_kot.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.mvvm_wan_kot.common.custom.ProgressDialogFragment
import com.example.mvvm_wan_kot.common.utils.ActivityManager
import com.example.mvvm_wan_kot.ui.login.LoginActivity

abstract class BaseVMFragment<VM : BaseViewModel>(useDataBinding: Boolean = true) : Fragment() {

    private val _useBinding = useDataBinding
    protected lateinit var mBinding: ViewDataBinding
    protected lateinit var mViewModel: VM
    private lateinit var progressDialogFragment: ProgressDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (_useBinding) {
            mBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
            mBinding.root
        } else
            inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = initVM()
        if (_useBinding) mBinding.lifecycleOwner = this
        initView()
        initData()
        startObserve()
    }

    abstract fun getLayoutResId(): Int
    abstract fun initVM(): VM
    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()

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

    fun showProgressDialog(@StringRes message: Int = 0, isCancel : Boolean = true) {
        if (!this::progressDialogFragment.isInitialized) {
            progressDialogFragment = ProgressDialogFragment.newInstance()
        }
        progressDialogFragment.show(childFragmentManager, message, isCancel)
    }

    fun hideProgressDialog() {
        if (this::progressDialogFragment.isInitialized && progressDialogFragment.isVisible) {
            progressDialogFragment.dismiss()
        }
    }
}