package com.example.diosoccernews.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.diosoccernews.data.News

@Database(entities = [News::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract val dao: NewsDao

    companion object {
        @Volatile
        private var INSTANCE: NewsDao? = null

        fun getDaoInstance(context: Context): NewsDao {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = buildDatabase(context).dao
                    INSTANCE = instance
                }
                return instance
            }
        }

        private fun buildDatabase(context: Context): NewsDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                NewsDatabase::class.java,
                "news-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
    }
}
