package com.example.mvvm_wan_kot.model.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class HotKey(
    @PrimaryKey(autoGenerate = true)
    var primaryKeyId : Int?,
    var id: Int = 0,
    var link: String?="",
    var name: String?="",
    var order: Int = 0,
    var visible: Int = 0
)