package com.example.diosoccernews.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diosoccernews.NewsApplication
import com.example.diosoccernews.data.News
import com.example.diosoccernews.data.local.AppDatabase
import com.example.diosoccernews.data.remote.NewsApiService
import com.example.diosoccernews.data.remote.RemoteNews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyViewModel : ViewModel() {
    val newsDao = AppDatabase.getDaoInstance(NewsApplication.getAppContext())
    private var newsApi = RemoteNews().buildApi()
    private val _newsList = MutableLiveData<List<News>>()
    init {
        getAllRemoteNews()      // Put the remote news on _newsList and on the db
    }
    val newsList: LiveData<List<News>> = _newsList

    private fun getAllRemoteNews() {
        newsApi.getAllNews().enqueue(
            object : Callback<List<News>> {
                override fun onResponse(
                    call: Call<List<News>>,
                    response: Response<List<News>>
                ) {
                    val a = response.body()?.let { news ->
                        _newsList.value = news
                        newsDao.addAllNews(news)
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