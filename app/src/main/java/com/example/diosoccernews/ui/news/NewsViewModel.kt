package com.example.diosoccernews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diosoccernews.data.News
import com.example.diosoccernews.data.remote.NewsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsViewModel : ViewModel() {
    lateinit var newsApi: NewsApiService
    private val _newsList = MutableLiveData<List<News>>()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mcrispim.github.io/dio-women-soccer-news-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsApi = retrofit.create(NewsApiService::class.java)
        newsApi.getAllNews().enqueue(
            object : Callback<List<News>> {
                override fun onResponse(
                    call: Call<List<News>>,
                    response: Response<List<News>>
                ) {
                    response.body()?.let { news ->
                        _newsList.value = news
                    }
                }

                override fun onFailure(
                    call: Call<List<News>>, t: Throwable
                ) {
                    t.printStackTrace()
                }
            }
        )
    }
    val newsList: LiveData<List<News>> = _newsList
}