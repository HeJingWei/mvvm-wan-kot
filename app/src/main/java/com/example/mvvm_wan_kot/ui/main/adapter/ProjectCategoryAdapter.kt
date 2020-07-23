package com.example.mvvm_wan_kot.ui.main.adapter

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.common.adapter.BaseBindAdapter
import com.example.mvvm_wan_kot.model.bean.ProjectChapter

class ProjectCategoryAdapter (layout :Int = R.layout.item_project_category_item) : BaseBindAdapter<ProjectChapter>(layout,BR.chapter) {
    private var checkPosition = 0
    override fun convert(holder: BaseDataBindingHolder<ViewDataBinding>, item: ProjectChapter) {
        super.convert(holder, item)
        val isCheck = holder.adapterPosition == checkPosition
        val bgColor = if (isCheck) context.getColor(R.color.main_select_color) else context.getColor(R.color.white)
        val textColor = if (isCheck) context.getColor(R.color.white) else context.getColor(R.color.main_unselect_color)
        holder.setTextColor(R.id.projectChapterName,textColor).setBackgroundColor(R.id.projectChapterName,bgColor)
    }

    fun setCheckPos(position:Int){
        checkPosition = position
        notifyDataSetChanged()
    }
}