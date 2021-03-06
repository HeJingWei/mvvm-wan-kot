package com.example.mvvm_wan_kot.ui.common.adapter

import android.view.LayoutInflater
import android.view.View
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.model.bean.HotKey
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.item_nav_tag.view.*

class TagFlowAdapter(private val tags: List<Article>) : TagAdapter<Article>(tags) {
    override fun getView(parent: FlowLayout?, position: Int, t: Article?): View {
        return LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_nav_tag, parent, false)
            .apply {
                tvTag.text = tags[position].title
            }
    }
}

class SearchTagAdapter(private val tags: List<HotKey>) : TagAdapter<HotKey>(tags) {
    override fun getView(parent: FlowLayout?, position: Int, t: HotKey?): View {
        return LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_nav_tag, parent, false)
            .apply {
                tvTag.text = tags[position].name
            }
    }
}