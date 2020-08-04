package com.example.mvvm_wan_kot.model.room

import androidx.room.*
import com.example.mvvm_wan_kot.model.bean.Article

@Dao
interface ReadHistoryDao {

    @Query("SELECT * FROM tb_article")
    suspend fun getAllHistory(): List<Article>

    @Query("SELECT * FROM tb_article WHERE id = (:id)")
    suspend fun getReadHistoryById(id: Int): Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg articles: Article)

    @Update
    suspend fun update(article: Article)

    @Delete
    suspend fun delete(vararg articles: Article)
}