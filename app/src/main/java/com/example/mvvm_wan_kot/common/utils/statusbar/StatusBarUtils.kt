package com.example.mvvm_wan_kot.common.utils.statusbar

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.core.graphics.ColorUtils
import com.example.mvvm_wan_kot.R


class StatusBarUtils {
    companion object {
        val FAKE_STATUS_BAR_VIEW_ID = R.id.fake_status_bar_view

        /**
         * 获得状态栏的高度
         */
        fun getHeight(context: Context): Int {
            var statusBarHeight = 0
            try {
                val resourceId: Int = context.resources.getIdentifier(
                    "status_bar_height", "dimen",
                    "android"
                )
                if (resourceId > 0) {
                    statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return statusBarHeight
        }

        /**
         * 设置状态栏颜色
         * @param context 上下文，尽量使用Activity
         * @param color   状态栏颜色
         */
        fun setColor(context: Context, @ColorInt color: Int) {
            if (context is Activity) {
                setColor((context as Activity).window, color)
            }
        }

        /**
         * 设置状态栏颜色
         *
         * @param window 窗口，可用于Activity和Dialog等
         * @param color  状态栏颜色
         */
        fun setColor(@NonNull window: Window, @ColorInt color: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                window.setStatusBarColor(color);
                setTextDark(window, !isDarkColor(color));
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setColor(window, ColorUtils.blendARGB(Color.TRANSPARENT, color, 0.5f), false);
            }
        }

        /**
         * Android 5.0 以下版本设置状态栏颜色
         *
         * @param window        窗口
         * @param color         状态栏颜色值
         * @param isTransparent 是否透明
         */
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        fun setColor(@NonNull window: Window, @ColorInt color: Int, isTransparent: Boolean) {
            val context: Context = window.context
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            val decorView: ViewGroup = window.decorView as ViewGroup
            val contentView =
                decorView.findViewById<View>(android.R.id.content)
            contentView?.setPadding(0, if (isTransparent) 0 else getHeight(context), 0, 0)
            val fakeStatusBarView =
                decorView.findViewById<View>(FAKE_STATUS_BAR_VIEW_ID)
            if (fakeStatusBarView != null) {
                fakeStatusBarView.setBackgroundColor(color)
                if (fakeStatusBarView.visibility == View.GONE) {
                    fakeStatusBarView.visibility = View.VISIBLE
                }
            } else {
                // 绘制一个和状态栏一样高的矩形
                val statusBarView = View(context)
                val layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    getHeight(context)
                )
                statusBarView.layoutParams = layoutParams
                statusBarView.setBackgroundColor(color)
                statusBarView.id = FAKE_STATUS_BAR_VIEW_ID
                decorView.addView(statusBarView)
            }
        }

        /**
         * 设置状态栏透明
         *
         * @param context 上下文，尽量使用Activity
         */
        fun setTransparent(context: Context) {
            if (context is Activity) {
                setTransparent((context as Activity).window)
            }
        }

        /**
         * 设置状态栏透明
         *
         * @param window 窗口，可用于Activity和Dialog等
         */
        fun setTransparent(@NonNull window: Window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                window.getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
                window.setStatusBarColor(Color.TRANSPARENT)
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                setColor(window, -0x80000000, true)
            }
        }

        /**
         * 设置状态栏是否为黑色文字
         *
         * @param context 上下文，尽量使用Activity
         * @param isDark  是否为黑色文字
         */
        fun setTextDark(context: Context, isDark: Boolean) {
            if (context is Activity) {
                setTextDark((context as Activity).window, isDark)
            }
        }

        /**
         * 设置状态栏是否为黑色文字
         *
         * @param window 窗口，可用于Activity和全屏Dialog
         * @param isDark 是否为黑色文字
         */
        fun setTextDark(window: Window, isDark: Boolean) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val decorView: View = window.getDecorView()
                val systemUiVisibility = decorView.systemUiVisibility
                if (isDark) {
                    decorView.systemUiVisibility =
                        systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                } else {
                    decorView.systemUiVisibility =
                        systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                }
            }
        }

        /**
         * 判断颜色是否为深色
         *
         * @param color 要判断的颜色
         * @return 是否为深色
         */
        fun isDarkColor(@ColorInt color:Int) = ColorUtils.calculateLuminance(color) < 0.5

    }
}