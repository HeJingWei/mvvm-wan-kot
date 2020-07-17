package com.example.mvvm_wan_kot.ui.setting

import android.view.Gravity
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.NavIconType
import com.example.mvvm_wan_kot.common.base.BaseVMActivity
import com.example.mvvm_wan_kot.common.ext.setOnClickListener
import com.example.mvvm_wan_kot.common.ext.setToolbar
import com.example.mvvm_wan_kot.common.ext.showToast
import com.liys.dialoglib.LDialog
import kotlinx.android.synthetic.main.activity_setting.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SettingActivity : BaseVMActivity<SettingViewModel>() {

    private lateinit var exitDialog: LDialog


    override fun getLayoutResId() = R.layout.activity_setting

    override fun initVM(): SettingViewModel = getViewModel()

    override fun initView() {
        mBinding.run {
            setVariable(BR.vm, mViewModel)
        }
        setToolbar(getString(R.string.home_mine_setting), NavIconType.BACK)
        exitDialog = LDialog(this@SettingActivity, R.layout.dialog_exit_login)
        exitDialog.setCanceledOnTouchOutside(false)
        exitDialog.setCancelable(false)
        setOnClickListener(settingExitLogin) {
            exitDialog.with()
                .setMaskValue(0.5f)
                .setWidthRatio(0.8)
                .setBgRadius(16)
                .setGravity(Gravity.CENTER)
                .setCancelBtn(R.id.exitLoginCancel)
                .setOnClickListener(
                    LDialog.DialogOnClickListener { _, _ ->
                        exitDialog.dismiss()
                        mViewModel.loginOut()
                    },
                    R.id.exitLoginConfirm
                )
                .show()
        }
    }

    override fun initData() {
        mViewModel.isExitLogin()
    }

    override fun startObserve() {
        mViewModel.apply {
            uiState.observe(this@SettingActivity, Observer {
                if (it.loginOutSuccess) {
                    showToast(R.string.exit_login_success)
                    finish()
                }
            })
        }
    }
}