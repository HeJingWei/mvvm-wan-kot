package com.example.mvvm_wan_kot.model.bean

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
//entity声明定义，并且指定了映射数据表名
@Entity(tableName = "tb_article")
data class Article(
    //设置主键，并且定义自增增
    @PrimaryKey(autoGenerate = true)
    var primaryKeyId: Int = 0,
    var apkLink: String? = "",
    var audit: Int = 0,
    var author: String? = "",
    var canEdit: Boolean = false,
    @ColumnInfo(name = "chapter_id")//定义数据表中的字段名
    var chapterId: Int = 0,
    @ColumnInfo(name = "chapter_name")
    var chapterName: String? = "",
    var collect: Boolean = false,
    var courseId: Int = 0,
    var desc: String? = "",
    var descMd: String? = "",
    @ColumnInfo(name = "envelope_pic")
    var envelopePic: String? = "",
    var fresh: Boolean = false,
    var id: Int = 0,
    var link: String? = "",
    @ColumnInfo(name = "nice_date")
    var niceDate: String? = "",
    @ColumnInfo(name = "nice_share_date")
    var niceShareDate: String? = "",
    var origin: String? = "",
    @ColumnInfo(name = "origin_id")
    var originId: Int = 0,
    var prefix: String? = "",
    @ColumnInfo(name = "project_link")
    var projectLink: String? = "",
    @ColumnInfo(name = "publish_time")
    var publishTime: Long = 0,
    @ColumnInfo(name = "real_super_chapter_id")
    var realSuperChapterId: Int = 0,
    @ColumnInfo(name = "self_visible")
    var selfVisible: Int = 0,
    @ColumnInfo(name = "share_date")
    var shareDate: Long = 0,
    @ColumnInfo(name = "share_user")
    var shareUser: String? = "",
    @ColumnInfo(name = "super_chapter_id")
    var superChapterId: Int = 0,
    @ColumnInfo(name = "super_chapter_name")
    var superChapterName: String? = "",
    @Ignore//指示Room需要忽略的字段或方法
    var tags: List<Tag> = emptyList(),
    var title: String? = "",
    var type: Int = 0,
    @ColumnInfo(name = "user_id")
    var userId: Int = 0,
    var visible: Int = 0,
    var zan: Int = 0
) : Parcelable