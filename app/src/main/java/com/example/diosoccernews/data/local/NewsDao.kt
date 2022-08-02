package com.example.diosoccernews.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.diosoccernews.data.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getAllNews(): List<News>

    @Query("SELECT * FROM news WHERE isFavorite = 1")
    fun getFavoriteNews(): List<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNews(news: News)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllNews(newsList: List<News>)
}