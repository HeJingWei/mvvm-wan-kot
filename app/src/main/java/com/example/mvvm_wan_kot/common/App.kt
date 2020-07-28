package com.example.mvvm_wan_kot.common

import android.app.Application
import com.chad.library.adapter.base.module.LoadMoreModuleConfig
import com.example.mvvm_wan_kot.common.custom.ActivityLifecycleCallbacksAdapter
import com.example.mvvm_wan_kot.common.custom.CommonLoadMoreView
import com.example.mvvm_wan_kot.common.di.appModule
import com.example.mvvm_wan_kot.common.utils.ActivityManager
import com.example.mvvm_wan_kot.common.utils.Timer
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    companion object {
        @JvmStatic
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        registerActivityCallbacks()
        LoadMoreModuleConfig.defLoadMoreView = CommonLoadMoreView()


        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@App)
            modules(appModule)
        }
    }

    private fun registerActivityCallbacks() {
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksAdapter(
            onActivityCreated = { activity, _ ->
                ActivityManager.activities.add(activity)
            },
            onActivityDestroyed = { activity ->
                ActivityManager.activities.remove(activity)
            }
        ))
    }
}

