package com.example.mvvm_wan_kot.ui.collect.adapter

import androidx.databinding.library.baseAdapters.BR
import com.chad.library.adapter.base.module.LoadMoreModule
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.BaseBindAdapter
import com.example.mvvm_wan_kot.model.bean.Article

class CollectAdapter(layout: Int = R.layout.item_my_collect_item) :
    BaseBindAdapter<Article>(layout, BR.article), LoadMoreModule {
    init {
        addChildClickViewIds(R.id.collectCancel)
    }
}