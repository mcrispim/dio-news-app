package com.example.diosoccernews.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diosoccernews.NewsApplication
import com.example.diosoccernews.data.News
import com.example.diosoccernews.data.local.NewsDatabase
import com.example.diosoccernews.data.remote.RemoteNews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel : ViewModel() {
    val newsDao = NewsDatabase.getDaoInstance(NewsApplication.getAppContext())
    private var newsApi = RemoteNews().buildApi()
    private val _newsList = MutableLiveData<List<News>>()
    init {
        getAllRemoteNews()      // Put the remote news on _newsList and on the db
    }
    val newsList: LiveData<List<News>> = _newsList

    fun updateNewsList() {
        _newsList.value = newsDao.getAllNews()
    }

    private fun getAllRemoteNews() {
        newsApi.getAllNews().enqueue(
            object : Callback<List<News>> {
                override fun onResponse(
                    call: Call<List<News>>,
                    response: Response<List<News>>
                ) {
                    response.body()?.let { news ->
                        _newsList.value = news
                        newsDao.addAllNews(news)
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