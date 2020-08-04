package com.example.mvvm_wan_kot.common.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm_wan_kot.common.custom.ProgressDialogFragment

abstract class BaseActivity: AppCompatActivity() {
    private lateinit var progressDialogFragment: ProgressDialogFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView()
        initData()
    }

    open fun getLayoutResId(): Int = 0
    abstract fun initView()
    abstract fun initData()

    fun showProgressDialog(@StringRes message: Int) {

        if (!this::progressDialogFragment.isInitialized) {
            progressDialogFragment = ProgressDialogFragment.newInstance()
        }
        progressDialogFragment.show(supportFragmentManager, message, false)
    }

    fun hideProgressDialog() {
        if (this::progressDialogFragment.isInitialized && progressDialogFragment.isVisible) {
            progressDialogFragment.dismiss()
        }
    }
}