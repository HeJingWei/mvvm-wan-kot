package com.example.mvvm_wan_kot.ui.integral.adapter

import androidx.databinding.library.baseAdapters.BR
import com.chad.library.adapter.base.module.LoadMoreModule
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.BaseBindAdapter
import com.example.mvvm_wan_kot.model.bean.IntegralBean

class IntegralRankAdapter (layout: Int = R.layout.item_integral_rank_item) :
    BaseBindAdapter<IntegralBean>(layout, BR.integral), LoadMoreModule

