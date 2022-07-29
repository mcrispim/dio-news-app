package com.example.diosoccernews

import android.app.Application
import android.content.Context

class NewsApplication: Application() {
    init { app = this }
    companion object {
        private lateinit var app: NewsApplication
        fun getAppContext(): Context =
            app.applicationContext
    }
}