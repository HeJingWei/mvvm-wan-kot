package com.example.mvvm_wan_kot.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvm_wan_kot.model.bean.Article
import com.example.mvvm_wan_kot.model.bean.HotKey

@Database(entities = [Article::class,HotKey::class],version = 2)
abstract class AppDatabase : RoomDatabase(){
    abstract fun readHistoryDao(): ReadHistoryDao

    abstract fun searchHistoryDao():SearchHistoryDao
}