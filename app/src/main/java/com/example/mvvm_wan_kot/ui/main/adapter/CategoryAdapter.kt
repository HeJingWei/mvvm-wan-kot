package com.example.mvvm_wan_kot.ui.main.adapter

import android.widget.CheckedTextView
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.BaseBindAdapter
import com.example.mvvm_wan_kot.model.bean.ProjectChapter

class CategoryAdapter(layoutResId: Int = R.layout.item_category_item) :
    BaseBindAdapter<ProjectChapter>(layoutResId, BR.chapter) {

    private var checkedPosition = 0

    override fun convert(holder: BaseDataBindingHolder<ViewDataBinding>, item: ProjectChapter) {
        super.convert(holder, item)
        holder.getView<CheckedTextView>(R.id.ctvCategory).isChecked =
            checkedPosition == holder.adapterPosition
    }

    fun check(position:Int){
        checkedPosition = position
        notifyDataSetChanged()
    }
}
