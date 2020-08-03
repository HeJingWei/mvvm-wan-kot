package com.example.mvvm_wan_kot.model.bean

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by xiaojianjun on 2019-11-07.
 */
@Parcelize
@Entity
data class Tag(
    @PrimaryKey(autoGenerate = true) //定义主键
    var id: Long,
    @ColumnInfo(name = "article_id") //定义数据表中的字段名
    var articleId: Long,
    var name: String?,
    var url: String?
) : Parcelable