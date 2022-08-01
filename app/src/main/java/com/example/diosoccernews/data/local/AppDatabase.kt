package com.example.diosoccernews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.diosoccernews.data.News

@Database(entities = [News::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
