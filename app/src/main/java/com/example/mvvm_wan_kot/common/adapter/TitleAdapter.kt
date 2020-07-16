package com.example.mvvm_wan_kot.common.adapter

import android.app.Activity
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.dylanc.loadinghelper.LoadingHelper
import com.example.mvvm_wan_kot.R


class TitleAdapter constructor(val title: String, val navIconType: NavIconType) :
    LoadingHelper.Adapter<TitleAdapter.ViewHolder>() {

    class ViewHolder(val toolbar: Toolbar) : LoadingHelper.ViewHolder(toolbar) {
        fun getActivity(): Activity = rootView.context as Activity
    }

    override fun onBindViewHolder(holder: ViewHolder) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.getActivity().window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (!title.isNullOrBlank()) {
            holder.toolbar.title = this@TitleAdapter.title
        }
        when (this@TitleAdapter.navIconType) {
            NavIconType.BACK -> {
                holder.toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_black)
                holder.toolbar.setNavigationOnClickListener {
                    holder.getActivity().finish()
                }
            }
            NavIconType.NONE -> {
                holder.toolbar.navigationIcon = null
            }
        }

    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder =
        TitleAdapter.ViewHolder(
            inflater.inflate(R.layout.layout_toolbar, parent, false) as Toolbar
        )
}


enum class NavIconType {
    BACK, NONE
}