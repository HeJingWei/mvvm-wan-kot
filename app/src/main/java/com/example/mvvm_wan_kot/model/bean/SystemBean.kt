package com.example.mvvm_wan_kot.model.bean

import java.io.Serializable

data class SystemBean(
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int,
    val children: ArrayList<SystemBean>
):Serializable