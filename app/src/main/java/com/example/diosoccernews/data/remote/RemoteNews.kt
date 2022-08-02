package com.example.diosoccernews.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteNews {
    fun buildApi(): NewsApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://mcrispim.github.io/dio-women-soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(NewsApiService::class.java)
    }
}