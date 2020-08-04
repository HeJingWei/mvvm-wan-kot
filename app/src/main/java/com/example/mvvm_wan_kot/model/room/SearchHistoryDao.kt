package com.example.mvvm_wan_kot.model.room

import androidx.room.*
import com.example.mvvm_wan_kot.model.bean.HotKey

@Dao
interface SearchHistoryDao {
    @Delete
    suspend fun delete(hotKey: HotKey)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hotKey: HotKey)

    @Query("SELECT * FROM search_history ORDER BY primaryKeyId DESC")
    suspend fun query(): MutableList<HotKey>

    @Update
    suspend fun update(hotKey: HotKey)
}