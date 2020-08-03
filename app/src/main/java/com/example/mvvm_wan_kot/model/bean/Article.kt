package com.example.mvvm_wan_kot.model.bean

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tb_article")//定义表名
data class Article(
    @PrimaryKey(autoGenerate = true)
    var primaryKeyId: Int = 0,
    val apkLink: String,
    val audit: Int,
    val author: String,
    val canEdit: Boolean,
    @ColumnInfo(name = "chapter_id")//定义数据表中的字段名
    val chapterId: Int,
    @ColumnInfo(name = "chapter_name")
    val chapterName: String,
    var collect: Boolean,
    val courseId: Int,
    val desc: String,
    val descMd: String,
    @ColumnInfo(name = "envelope_pic")
    val envelopePic: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    @ColumnInfo(name = "nice_date")
    val niceDate: String,
    @ColumnInfo(name = "nice_share_date")
    val niceShareDate: String,
    val origin: String,
    @ColumnInfo(name = "origin_id")
    val originId :Int,
    val prefix: String,
    @ColumnInfo(name = "project_link")
    val projectLink: String,
    @ColumnInfo(name = "publish_time")
    val publishTime: Long,
    @ColumnInfo(name = "real_super_chapter_id")
    val realSuperChapterId: Int,
    @ColumnInfo(name = "self_visible")
    val selfVisible: Int,
    @ColumnInfo(name = "share_date")
    val shareDate: Long,
    @ColumnInfo(name = "share_user")
    val shareUser: String,
    @ColumnInfo(name = "super_chapter_id")
    val superChapterId: Int,
    @ColumnInfo(name = "super_chapter_name")
    val superChapterName: String,
    @Ignore//指示Room需要忽略的字段或方法
    var tags: List<Tag> = emptyList(),
    val title: String,
    val type: Int,
    @ColumnInfo(name = "user_id")
    val userId: Int,
    val visible: Int,
    val zan: Int
) : Parcelable