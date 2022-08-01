package com.example.diosoccernews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.diosoccernews.NewsApplication
import com.example.diosoccernews.data.News
import com.example.diosoccernews.data.local.AppDatabase
import com.example.diosoccernews.data.remote.NewsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsViewModel : ViewModel() {
    var newsApi: NewsApiService
    private val _newsList = MutableLiveData<List<News>>()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://mcrispim.github.io/dio-women-soccer-news-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsApi = retrofit.create(NewsApiService::class.java)

        val db = Room.databaseBuilder(
            NewsApplication.getAppContext(),
            AppDatabase::class.java,
            "news-database"
        ).build()

        getAllRemoteNews()
    }

    val newsList: LiveData<List<News>> = _newsList

    private fun getAllRemoteNews() {
        newsApi.getAllNews().enqueue(
            object : Callback<List<News>> {
                override fun onResponse(
                    call: Call<List<News>>,
                    response: Response<List<News>>
                ) {
                    response.body()?.let { news ->
                        _newsList.value = news
                        // TODO: melhorar resposta em caso de falha
                    }
                }

                override fun onFailure(
                    call: Call<List<News>>, t: Throwable
                ) {
                    // TODO: melhorar resposta em caso de falha
                    t.printStackTrace()
                }
            }
        )
    }
}