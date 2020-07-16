package com.example.mvvm_wan_kot.common.adapter

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder

open class BaseBindAdapter<T>(layoutResId: Int, br: Int) :
    BaseQuickAdapter<T, BaseDataBindingHolder<ViewDataBinding>>(layoutResId),LoadMoreModule{

    private val _br: Int = br

    override fun onItemViewHolderCreated(
        viewHolder: BaseDataBindingHolder<ViewDataBinding>,
        viewType: Int
    ) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseDataBindingHolder<ViewDataBinding>, item: T) {
        holder.dataBinding?.run {
            setVariable(_br, item)
            executePendingBindings()
        }
    }
}