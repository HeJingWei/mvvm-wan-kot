package com.example.mvvm_wan_kot.ui.splash

import android.graphics.Color
import android.os.Build
import android.view.WindowManager
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.base.BaseActivity
import com.example.mvvm_wan_kot.common.utils.ActivityManager
import com.example.mvvm_wan_kot.common.utils.statusbar.StatusBarUtils
import com.example.mvvm_wan_kot.ui.main.MainActivity


class SplashActivity : BaseActivity() {
    override fun getLayoutResId() = R.layout.activity_splash

    override fun initView() {
        // 适配刘海屏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val layoutParams = window.attributes
            layoutParams.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = layoutParams
        }
        // 适配Android 4.4
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            StatusBarUtils.setColor(getWindow(), Color.TRANSPARENT, true);
        }
        StatusBarUtils.setTransparent(this)
        window.decorView.postDelayed({
            ActivityManager.start(MainActivity::class.java)
            ActivityManager.finish(SplashActivity::class.java)
        }, 3000)
    }

    override fun initData() {

    }

}