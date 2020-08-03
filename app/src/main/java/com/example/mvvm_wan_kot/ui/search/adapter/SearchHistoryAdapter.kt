package com.example.mvvm_wan_kot.ui.search.adapter

import androidx.databinding.library.baseAdapters.BR
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.BaseBindAdapter
import com.example.mvvm_wan_kot.model.bean.HotKey

class SearchHistoryAdapter(layout : Int = R.layout.item_search_history_item ) : BaseBindAdapter<HotKey>(layout,BR.key) {
    init {
        addChildClickViewIds(R.id.searchHistoryDelete)
    }
}