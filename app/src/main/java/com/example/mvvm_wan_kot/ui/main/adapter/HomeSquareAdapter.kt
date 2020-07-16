package com.example.mvvm_wan_kot.ui.main.adapter

import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.BaseBindAdapter
import com.example.mvvm_wan_kot.model.bean.Article

class HomeSquareAdapter(layout: Int = R.layout.item_home_square_item) :
    BaseBindAdapter<Article>(layout, BR.article) {
    init {
        addChildClickViewIds(R.id.collect)
    }

    override fun convert(holder: BaseDataBindingHolder<ViewDataBinding>, item: Article) {
        super.convert(holder, item)
        val view = holder.getView<ImageView>(R.id.collect)

        view.setColorFilter(
            if (item.collect) context.getColor(R.color.main_select_color) else context.getColor(
                R.color.main_unselect_color
            )
        )
    }
}