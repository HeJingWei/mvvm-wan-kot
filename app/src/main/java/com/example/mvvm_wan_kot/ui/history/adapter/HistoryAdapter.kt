package com.example.mvvm_wan_kot.ui.history.adapter

import androidx.databinding.library.baseAdapters.BR
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.BaseBindAdapter
import com.example.mvvm_wan_kot.model.bean.Article

class HistoryAdapter(layout : Int = R.layout.item_history_item) : BaseBindAdapter<Article>(layout,BR.article) {
    init {
        addChildClickViewIds(R.id.delete)
    }
}