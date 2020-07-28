package com.example.mvvm_wan_kot.ui.main.system.adapter

import android.widget.TextView
import androidx.core.text.htmlEncode
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.mvvm_wan_kot.R
import com.example.mvvm_wan_kot.model.bean.SystemBean

class SysAdapter (layout : Int = R.layout.item_sys_item) : BaseQuickAdapter<SystemBean,BaseViewHolder>(layout) {
    override fun convert(holder: BaseViewHolder, item: SystemBean) {
        holder.setText(R.id.sysTitle,item.name.htmlEncode())
        val contentView = holder.getView<TextView>(R.id.SysContent)
        var content:StringBuilder = StringBuilder()
        item.children.forEach {
            content.append(it.name)
            content.append("    ")
        }
        contentView.text = content.toString()
    }
}