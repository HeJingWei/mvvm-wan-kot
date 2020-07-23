package com.example.mvvm_wan_kot.ui.main.adapter

import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.BaseBindAdapter
import com.example.mvvm_wan_kot.common.utils.loadImage
import com.example.mvvm_wan_kot.model.bean.Article

class HomeProjectAdapter(layout: Int = R.layout.item_home_project_item) :
    BaseBindAdapter<Article>(layout, BR.article) {
    override fun convert(holder: BaseDataBindingHolder<ViewDataBinding>, item: Article) {
        super.convert(holder, item)
        holder.getView<ImageView>(R.id.projectCover).loadImage(item.envelopePic)
    }
}