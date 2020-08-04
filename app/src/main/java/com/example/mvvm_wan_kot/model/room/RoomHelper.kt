package com.example.mvvm_wan_kot.model.room

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mvvm_wan_kot.common.App

object RoomHelper {
    private val appDatabase by lazy {
        Room.databaseBuilder(App.instance, AppDatabase::class.java, "database_wanandroid")
            .fallbackToDestructiveMigration()
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    val readHistoryDao by lazy { appDatabase.readHistoryDao() }

    val searchHistoryDao by lazy { appDatabase.searchHistoryDao() }

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "CREATE TABLE IF NOT EXISTS `search_history` (`primaryKeyId` INTEGER PRIMARY KEY AUTOINCREMENT, `id` INTEGER NOT NULL, `link` TEXT, `name` TEXT, `order` INTEGER NOT NULL, `visible` INTEGER NOT NULL)"
            )
        }
    }
}