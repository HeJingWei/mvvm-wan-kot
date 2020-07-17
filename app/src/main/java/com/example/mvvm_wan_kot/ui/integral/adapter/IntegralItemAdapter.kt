package com.example.mvvm_wan_kot.ui.integral.adapter

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.BaseBindAdapter
import com.example.mvvm_wan_kot.common.ext.toDateTime
import com.example.mvvm_wan_kot.model.bean.IntegralItem

class IntegralItemAdapter(layout: Int = R.layout.item_integral_item) :
    BaseBindAdapter<IntegralItem>(layout, BR.integral),LoadMoreModule {
    override fun convert(holder: BaseDataBindingHolder<ViewDataBinding>, item: IntegralItem) {
        super.convert(holder, item)
        holder.setText(R.id.integralItemDate, item.date.toDateTime("YYYY-MM-dd HH:mm:ss"))
    }
}