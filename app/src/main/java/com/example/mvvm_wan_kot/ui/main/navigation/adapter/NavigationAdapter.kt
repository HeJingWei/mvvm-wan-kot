package com.example.mvvm_wan_kot.ui.main.navigation.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.model.bean.Navigation
import com.example.mvvm_wan_kot.ui.common.adapter.TagFlowAdapter
import kotlinx.android.synthetic.main.item_navigation_item.view.*

class NavigationAdapter(layout: Int = R.layout.item_navigation_item) :
    BaseQuickAdapter<Navigation, BaseViewHolder>(layout) {

    var onTagClickListener: ((article: Article) -> Unit)? = null

    override fun convert(holder: BaseViewHolder, item: Navigation) {
        holder.itemView.run {
            navigationTitle.text = item.name
            navigationTagFlow.adapter = TagFlowAdapter(item.articles)
            navigationTagFlow.setOnTagClickListener { _, position, _ ->
                onTagClickListener?.invoke(item.articles[position])
                true
            }
        }
    }
}