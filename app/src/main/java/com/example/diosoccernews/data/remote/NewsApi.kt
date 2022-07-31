package com.example.diosoccernews.data.remote

import com.example.diosoccernews.data.News
import retrofit2.Call
import retrofit2.http.GET

interface NewsApiService {
    @GET("news.json")
    fun getAllNews(): Call<List<News>>
}